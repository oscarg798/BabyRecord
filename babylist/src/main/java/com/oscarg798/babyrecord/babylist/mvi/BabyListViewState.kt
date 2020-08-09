package com.oscarg798.babyrecord.babylist.mvi

import com.oscarg798.babyrecord.babylist.failures.BabyListFailure
import com.oscarg798.babyrecord.babylist.models.ViewBaby
import com.oscarg798.babyrecord.core.base.ViewState

data class BabyListViewState(
    override val isIdling: Boolean,
    val isLoading: Boolean,
    val babies: Collection<ViewBaby>?,
    val error: BabyListFailure? = null
) : ViewState {

    companion object {

        fun init() = BabyListViewState(
            isIdling = true,
            isLoading = false,
            babies = null,
            error = null
        )
    }
}
