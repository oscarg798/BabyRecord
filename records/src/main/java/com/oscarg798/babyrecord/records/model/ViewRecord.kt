package com.oscarg798.babyrecord.records.model

import androidx.annotation.DrawableRes
import com.oscarg798.babyrecord.core.models.BabyRecordType

data class ViewRecord(
    val id: String,
    val type: BabyRecordType,
    @DrawableRes val iconId: Int,
    val startTime: String,
    val endTime: String? = null
)
