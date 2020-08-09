package com.oscarg798.babyrecord.records.model

import com.oscarg798.babyrecord.core.models.BabyRecordType
import com.oscarg798.babyrecord.core.models.Record
import java.text.SimpleDateFormat
import java.util.*

data class ViewRecord(
    val id: String,
    val type: BabyRecordType,
    val startTime: String,
    val endTime: String? = null
)
