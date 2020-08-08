package com.oscarg798.babyrecord.core.models


data class Record(
    override val id: String,
    override val babyId: String,
    override val type: BabyRecordType,
    val startTime: Long,
    var endTime: Long?
) : BabyRecord
