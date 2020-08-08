package com.oscarg798.babyrecord.core.di

import com.oscarg798.babyrecord.core.repository.RecordRepository
import com.oscarg798.babyrecord.core.repository.RecordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindRecordRepository(
        recordRepositoryImpl: RecordRepositoryImpl
    ): RecordRepository
}
