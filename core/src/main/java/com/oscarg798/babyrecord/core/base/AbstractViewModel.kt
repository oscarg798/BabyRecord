package com.oscarg798.babyrecord.core.base

import androidx.lifecycle.ViewModel
import com.oscarg798.babyrecord.core.base.Result as MVIResult
import com.oscarg798.babyrecord.core.base.ViewState as MVIViewState
import com.oscarg798.babyrecord.core.base.Wish as MVIWish
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.scan

abstract class AbstractViewModel<Wish : MVIWish, Result : MVIResult, ViewState : MVIViewState>(
    initialState: ViewState
) : ViewModel() {

    protected abstract val reducer: Reducer<Result, ViewState>
    protected abstract val coroutineContextProvider: CoroutineContextProvider

    private val wishProcessor = ConflatedBroadcastChannel<Wish>()

    val state: Flow<ViewState> = wishProcessor.asFlow()
        .flatMapMerge {
            getResult(it)
        }.scan(initialState) { state, result ->
            reducer.reduce(state, result)
        }

    protected val defaultExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception -> throw exception }

    protected abstract suspend fun getResult(wish: Wish): Flow<Result>


    fun onWish(wish: Wish) {
        wishProcessor.offer(wish)
    }

    override fun onCleared() {
        wishProcessor.close()
        super.onCleared()
    }
}
