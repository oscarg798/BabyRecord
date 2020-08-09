package com.oscarg798.babyrecord.records.di

import androidx.lifecycle.ViewModel
import com.oscarg798.babyrecord.core.base.Reducer
import com.oscarg798.babyrecord.core.base.di.ViewModelKey
import com.oscarg798.babyrecord.records.RecordsViewModel
import com.oscarg798.babyrecord.records.mvi.RecordsReducer
import com.oscarg798.babyrecord.records.mvi.RecordsViewState
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object RecordsModule {

    @IntoMap
    @ViewModelKey(RecordsViewModel::class)
    @Provides
    fun provideViewModel(recordsViewModel: RecordsViewModel): ViewModel = recordsViewModel

    @Provides
    fun provideRecordsReducer(recordsReducer: RecordsReducer): Reducer<@JvmSuppressWildcards com.oscarg798.babyrecord.records.mvi.RecordsResult, @JvmSuppressWildcards RecordsViewState> = recordsReducer

}
