package com.oscarg798.babyrecord.core.base

import com.oscarg798.babyrecord.core.base.Result as MVIResult
import com.oscarg798.babyrecord.core.base.ViewState as MVIViewState

interface Reducer<in Result : MVIResult, ViewState : MVIViewState> {

    suspend fun reduce(state: ViewState, from: Result): ViewState
}
