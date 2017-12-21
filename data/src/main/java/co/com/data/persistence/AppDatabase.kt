package co.com.data.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import co.com.data.persistence.dao.RecordDAO
import co.com.data.persistence.entities.DBRecord

/**
 * Created by oscarg798 on 12/20/17.
 */
@Database(entities = arrayOf(DBRecord::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recordDAO(): RecordDAO
}