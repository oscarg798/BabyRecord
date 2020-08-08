package co.com.babyrecord.ui.records.mvi

import com.oscarg798.babyrecord.core.base.Reducer
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class RecordsReducer @Inject constructor() : Reducer<@JvmSuppressWildcards RecordsResult, @JvmSuppressWildcards RecordsViewState> {

    override fun reduce(state: RecordsViewState, from: RecordsResult): RecordsViewState = when (from) {
        is RecordsResult.Loading -> state.copy(isLoading = true, error = null)
        is RecordsResult.RecordsFound -> state.copy(isLoading = false, records = from.records, error = null)
        is RecordsResult.Error -> state.copy(isLoading = false, error = from.failure)
    }
}
