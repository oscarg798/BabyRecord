package com.oscarg798.babyrecord.babylist.mvi

import com.oscarg798.babyrecord.core.base.Wish

sealed class BabyListWish : Wish {

    object AddBaby : BabyListWish()
    object GetBabies : BabyListWish()
}
