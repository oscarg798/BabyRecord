package com.oscarg798.babyrecord.records.usecases

import com.oscarg798.babyrecord.core.models.Record
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

typealias RecordStartAndEndTime = Pair<String, String?>

class GetRecordStartAndEndTimeFormatted @Inject constructor() {

    private val simpleDateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).apply {
        timeZone = TimeZone.getDefault()
    }

    operator fun invoke(record: Record) =
        RecordStartAndEndTime(simpleDateFormat.format(Date(record.startTime)),
            record.endTime?.let {
                simpleDateFormat.format(Date(it))
            })
}

fun RecordStartAndEndTime.getStartTime() = first
fun RecordStartAndEndTime.getEndTime() = second


private const val TIME_FORMAT = "HH:mm aa"
