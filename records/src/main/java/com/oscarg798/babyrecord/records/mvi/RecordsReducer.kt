package com.oscarg798.babyrecord.records.mvi

import com.oscarg798.babyrecord.core.base.Reducer
import com.oscarg798.babyrecord.core.models.BabyRecordType
import com.oscarg798.babyrecord.records.R
import com.oscarg798.babyrecord.records.model.ViewRecord
import com.oscarg798.babyrecord.records.usecases.GetRecordStartAndEndTimeFormatted
import com.oscarg798.babyrecord.records.usecases.getEndTime
import com.oscarg798.babyrecord.records.usecases.getStartTime
import javax.inject.Inject

class RecordsReducer @Inject constructor(private val getRecordStartAndEndTimeFormatted: GetRecordStartAndEndTimeFormatted) :
    Reducer<@JvmSuppressWildcards RecordsResult, @JvmSuppressWildcards RecordsViewState> {

    override suspend fun reduce(state: RecordsViewState, from: RecordsResult): RecordsViewState =
        when (from) {
            is RecordsResult.Loading -> state.copy(isLoading = true, error = null)
            is RecordsResult.RecordsFound -> state.copy(
                isLoading = false,
                records = mapRecords(from),
                error = null
            )
            is RecordsResult.Error -> state.copy(isLoading = false, error = from.failure)
        }

    private fun mapRecords(from: RecordsResult.RecordsFound): List<ViewRecord> {
        return from.records.map { record ->
            val recordStartAndEndTimeFormatted = getRecordStartAndEndTimeFormatted(record)
            ViewRecord(
                record.id,
                record.type,
                when (record.type) {
                    is BabyRecordType.Sleep -> R.drawable.ic_sleep_record
                    is BabyRecordType.Feed -> R.drawable.ic_feeding
                    is BabyRecordType.Diaper -> R.drawable.ic_diaper
                    is BabyRecordType.Medicine -> R.drawable.ic_medicine
                    is BabyRecordType.Size -> R.drawable.ic_measure
                },
                recordStartAndEndTimeFormatted.getStartTime(),
                recordStartAndEndTimeFormatted.getEndTime()
            )
        }
    }


}