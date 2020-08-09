package com.oscarg798.babyrecord.records

import com.oscarg798.babyrecord.records.mvi.RecordWish
import com.oscarg798.babyrecord.records.mvi.RecordsResult
import com.oscarg798.babyrecord.records.mvi.RecordsViewState
import com.oscarg798.babyrecord.core.base.AbstractViewModel
import com.oscarg798.babyrecord.core.base.CoroutineContextProvider
import com.oscarg798.babyrecord.core.base.Reducer
import com.oscarg798.babyrecord.records.usecases.GetRecordsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RecordsViewModel @Inject constructor(
    private val getRecordsUseCase: GetRecordsUseCase,
    override val reducer: Reducer<@JvmSuppressWildcards RecordsResult, @JvmSuppressWildcards RecordsViewState>,
    override val coroutineContextProvider: CoroutineContextProvider
) : AbstractViewModel<RecordWish, RecordsResult, RecordsViewState>(
    RecordsViewState.init()
) {

    override suspend fun getResult(wish: RecordWish): Flow<RecordsResult> = getRecordsResult()

    private suspend fun getRecordsResult() = getRecordsUseCase().map {
        RecordsResult.RecordsFound(it) as RecordsResult
    }.onStart {
        emit(RecordsResult.Loading)
    }.flowOn(coroutineContextProvider.backgroundDispatcher)
}
