package com.oscarg798.babyrecord.babylist.mvi

import com.oscarg798.babyrecord.babylist.failures.BabyListFailure
import com.oscarg798.babyrecord.core.base.Result
import com.oscarg798.babyrecord.core.models.Baby

sealed class BabyListResult : Result {

    object Loading : BabyListResult()
    data class BabiesFound(val records: Collection<Baby>) : BabyListResult()
    data class Error(val failure: BabyListFailure) : BabyListResult()
}
