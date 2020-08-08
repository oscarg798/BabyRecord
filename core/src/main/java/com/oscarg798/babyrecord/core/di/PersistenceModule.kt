package com.oscarg798.babyrecord.core.di

import android.content.Context
import androidx.room.Room
import com.oscarg798.babyrecord.core.persistence.BabyRecordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object PersistenceModule {

    @Provides
    fun provideDatabase(context: Context): BabyRecordDatabase {
        return Room.databaseBuilder(
            context,
            BabyRecordDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideRecordDAO(database: BabyRecordDatabase) = database.recordDAO()

    @Provides
    fun provideBabyDAO(database: BabyRecordDatabase) = database.babyDAO()

    @Provides
    fun provideSizeRecordDAO(database: BabyRecordDatabase) = database.sizeRecordDAO()
}

private const val DATABASE_NAME = "babyrecord.db"
