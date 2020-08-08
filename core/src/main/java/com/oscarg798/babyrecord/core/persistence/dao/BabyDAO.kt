package com.oscarg798.babyrecord.core.persistence.dao

import androidx.room.*
import com.oscarg798.babyrecord.core.persistence.entities.DBBaby
import kotlinx.coroutines.flow.Flow

/**
 * Created by oscarg798 on 12/21/17.
 */
@Dao
interface BabyDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dbBaby: DBBaby)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(dbBaby: DBBaby)

    @Delete
    suspend fun delete(dbBaby: DBBaby)

    @Query("select * from baby")
    fun get(): Flow<List<DBBaby>>
}
