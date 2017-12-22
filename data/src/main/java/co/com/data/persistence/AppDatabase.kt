package co.com.data.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import co.com.data.persistence.dao.BabyDAO

import co.com.data.persistence.dao.RecordDAO
import co.com.data.persistence.entities.DBBaby
import co.com.data.persistence.entities.DBRecord
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration


/**
 * Created by oscarg798 on 12/20/17.
 */
@Database(entities = [(DBRecord::class), (DBBaby::class)], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recordDAO(): RecordDAO

    abstract fun babyDAO(): BabyDAO
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `baby`" +
                " (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `birthDate` INTEGER NOT NULL," +
                " `weight` INTEGER NOT NULL, `height` INTEGER NOT NULL, PRIMARY KEY(`uuid`))")

    }
}

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL("CREATE TABLE IF NOT EXISTS `baby2`" +
                " (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `birthDate` INTEGER NOT NULL," +
                " `weight` INTEGER NULL, `height` INTEGER NULL, PRIMARY KEY(`uuid`))")

        database.execSQL("insert into baby2 select * from baby")
        database.execSQL("drop table baby")
        database.execSQL("ALTER TABLE baby2 rename to baby")

    }
}