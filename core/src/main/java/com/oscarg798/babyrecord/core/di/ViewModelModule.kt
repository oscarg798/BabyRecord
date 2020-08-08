package com.oscarg798.babyrecord.core.di

import androidx.lifecycle.ViewModelProvider
import com.oscarg798.babyrecord.core.base.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
