package com.oscarg798.babyrecord.babylist.mvi

import com.oscarg798.babyrecord.babylist.models.ViewBaby
import com.oscarg798.babyrecord.babylist.usecases.GetBabyAgeUseCase
import com.oscarg798.babyrecord.core.base.Reducer
import javax.inject.Inject

class BabyListReducer @Inject constructor(private val getBabyAgeUseCase: GetBabyAgeUseCase) :
    Reducer<BabyListResult, BabyListViewState> {

    override suspend fun reduce(state: BabyListViewState, from: BabyListResult): BabyListViewState =
        when (from) {
            is BabyListResult.BabiesFound -> state.copy(
                isLoading = true,
                isIdling = false,
                error = null,
                babies = from.records.map { baby ->
                    ViewBaby(baby, getBabyAgeUseCase(baby))
                }
            )
            is BabyListResult.Loading -> state.copy(
                isLoading = true,
                isIdling = false,
                error = null
            )
            is BabyListResult.Error -> state.copy(
                isIdling = false,
                isLoading = false,
                error = from.failure
            )
        }
}
