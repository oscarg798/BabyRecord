package co.com.babyrecord.di

import androidx.lifecycle.ViewModel
import co.com.babyrecord.ui.records.RecordsViewModel
import co.com.babyrecord.ui.records.mvi.RecordsReducer
import co.com.babyrecord.ui.records.mvi.RecordsResult
import co.com.babyrecord.ui.records.mvi.RecordsViewState
import com.oscarg798.babyrecord.core.base.Reducer
import com.oscarg798.babyrecord.core.base.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@InstallIn(FragmentComponent::class)
@Module
object RecordsModule {

    @IntoMap
    @ViewModelKey(RecordsViewModel::class)
    @Provides
    fun provideViewModel(recordsViewModel: RecordsViewModel): ViewModel = recordsViewModel

    @Provides
    fun provideRecordsReducer(recordsReducer: RecordsReducer): Reducer<@JvmSuppressWildcards RecordsResult, @JvmSuppressWildcards RecordsViewState> = recordsReducer
}
