package com.oscarg798.babyrecord.core.models

data class SizeRecord(
    override val id: String,
    override val babyId: String,
    val height: Float,
    val weight: Float,
    val measureDate: Long
) : BabyRecord {

    override val type: BabyRecordType
        get() = BabyRecordType.Size
}
