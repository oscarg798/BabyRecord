package com.oscarg798.babyrecord.core.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oscarg798.babyrecord.core.persistence.dao.BabyDAO
import com.oscarg798.babyrecord.core.persistence.dao.RecordDAO
import com.oscarg798.babyrecord.core.persistence.dao.SizeRecordDAO
import com.oscarg798.babyrecord.core.persistence.entities.DBBaby
import com.oscarg798.babyrecord.core.persistence.entities.DBRecord
import com.oscarg798.babyrecord.core.persistence.entities.DBSizeRecord


/**
 * Created by oscarg798 on 12/20/17.
 */
@Database(entities = [DBRecord::class, DBBaby::class, DBSizeRecord::class], version = 1)
abstract class BabyRecordDatabase : RoomDatabase() {

    abstract fun recordDAO(): RecordDAO

    abstract fun babyDAO(): BabyDAO

    abstract fun sizeRecordDAO(): SizeRecordDAO

    companion object {
        @Volatile
        private var INSTANCE: BabyRecordDatabase? = null

        @Synchronized
        fun getInstance(context: Context): BabyRecordDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    BabyRecordDatabase::class.java, DATABASE_NAME
                ).build()
                INSTANCE = instance
            }

            return instance
        }
    }
}

private const val DATABASE_NAME = "babyrecord.db"


