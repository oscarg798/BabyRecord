package com.oscarg798.babyrecord.core.persistence.dao

import androidx.room.*
import com.oscarg798.babyrecord.core.persistence.entities.DBSizeRecord
import kotlinx.coroutines.flow.Flow

/**
 * Created by oscarg798 on 12/22/17.
 */
@Dao
interface SizeRecordDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dbSizeRecord: DBSizeRecord)

    @Query("select * from size_record")
    fun get(): Flow<List<DBSizeRecord>>

    @Delete
    suspend fun delete(dbSizeRecord: DBSizeRecord)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(dbSizeRecord: DBSizeRecord)

}
