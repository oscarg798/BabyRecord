package com.oscarg798.babyrecord.core.base

import kotlinx.coroutines.CoroutineDispatcher

class CoroutineContextProvider(
    val mainDispatcher: CoroutineDispatcher,
    val backgroundDispatcher: CoroutineDispatcher
)
