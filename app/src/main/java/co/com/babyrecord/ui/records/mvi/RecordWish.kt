package co.com.babyrecord.ui.records.mvi

import com.oscarg798.babyrecord.core.base.Wish

sealed class RecordWish : Wish {

    object GetRecords : RecordWish()
}
