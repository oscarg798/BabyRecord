package com.oscarg798.babyrecord.core.di

import android.content.Context
import com.oscarg798.babyrecord.core.base.CoroutineContextProvider
import com.oscarg798.babyrecord.core.repository.RecordRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface RecordModuleDependencies {

    @ApplicationContext
    fun provideContext(): Context

    fun provideCoroutineContextProvider(): CoroutineContextProvider

    fun provideRecordsRepository(): RecordRepository
}
