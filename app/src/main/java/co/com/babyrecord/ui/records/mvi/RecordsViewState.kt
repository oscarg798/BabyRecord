package co.com.babyrecord.ui.records.mvi

import co.com.babyrecord.ui.records.failures.RecordsFailure
import com.oscarg798.babyrecord.core.base.ViewState
import com.oscarg798.babyrecord.core.models.Record

data class RecordsViewState(
    override val isIdling: Boolean,
    val isLoading: Boolean,
    val records: Collection<Record>?,
    val error: RecordsFailure? = null
) : ViewState {

    companion object {

        fun init() = RecordsViewState(
            isIdling = true,
            isLoading = false,
            records = null,
            error = null
        )
    }
}
