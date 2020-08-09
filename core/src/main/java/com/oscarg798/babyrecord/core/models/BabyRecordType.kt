package com.oscarg798.babyrecord.core.models

sealed class BabyRecordType(val name: String) {

    object Medicine : BabyRecordType(MEDICINE_TYPE)
    object Diaper : BabyRecordType(DIAPER_TYPE)
    object Sleep : BabyRecordType(SLEEP_TYPE)
    object Size : BabyRecordType(SIZE)
    object Feed : BabyRecordType(FEED_TYPE)

    companion object {
        fun getTypeFromName(name: String) =  BabyRecordType::class.nestedClasses
            .map { klass -> klass.objectInstance }
            .filterIsInstance<BabyRecordType>()
            .first {
                it.name == name
            }
    }
}

private const val SIZE = "size"
private const val DIAPER_TYPE = "diaper"
private const val SLEEP_TYPE = "sleep"
private const val FEED_TYPE = "feed"
private const val MEDICINE_TYPE = "medicine"
