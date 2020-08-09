package com.oscarg798.babyrecord.records.mvi

import com.oscarg798.babyrecord.records.failures.RecordsFailure
import com.oscarg798.babyrecord.core.base.ViewState
import com.oscarg798.babyrecord.core.models.Record
import com.oscarg798.babyrecord.records.model.ViewRecord

data class RecordsViewState(
    override val isIdling: Boolean,
    val isLoading: Boolean,
    val records: Collection<ViewRecord>?,
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
