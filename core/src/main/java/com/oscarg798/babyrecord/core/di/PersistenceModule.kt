package com.oscarg798.babyrecord.core.di

import android.content.Context
import androidx.room.Room
import com.oscarg798.babyrecord.core.persistence.BabyRecordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object PersistenceModule {

    @Provides
    fun provideRecordDAO(@ApplicationContext context: Context) =
        BabyRecordDatabase.getInstance(context).recordDAO()

    @Provides
    fun provideBabyDAO(@ApplicationContext context: Context) =
        BabyRecordDatabase.getInstance(context).babyDAO()

    @Provides
    fun provideSizeRecordDAO(@ApplicationContext context: Context) =
        BabyRecordDatabase.getInstance(context).sizeRecordDAO()
}

