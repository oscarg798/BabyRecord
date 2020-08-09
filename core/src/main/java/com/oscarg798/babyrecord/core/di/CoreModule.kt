package com.oscarg798.babyrecord.core.di

import com.oscarg798.babyrecord.core.base.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import java.util.Calendar

@InstallIn(ApplicationComponent::class)
@Module
object CoreModule {

    @Provides
    fun provideCoroutineContextProvider() =
        CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)

    @Provides
    fun provideCalendar() = Calendar.getInstance()
}
