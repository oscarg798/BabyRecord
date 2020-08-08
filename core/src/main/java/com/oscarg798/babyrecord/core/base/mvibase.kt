package com.oscarg798.babyrecord.core.base

interface Wish

interface Result

interface ViewState{
    val isIdling: Boolean

    sealed class LoadingState {
        object None : LoadingState()
        object Loading : LoadingState()
    }
}
