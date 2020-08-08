package com.oscarg798.babyrecord.core.persistence.dao

import androidx.room.*
import com.oscarg798.babyrecord.core.persistence.entities.DBRecord
import kotlinx.coroutines.flow.Flow

/**
 * Created by oscarg798 on 12/20/17.
 */
@Dao
interface RecordDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dbRecord: DBRecord)

    @Query("select * from record")
    fun getRecords(): Flow<List<DBRecord>>

    @Delete
    suspend fun delete(dbRecord: DBRecord)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(dbRecord: DBRecord)
}
