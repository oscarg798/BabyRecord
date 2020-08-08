package com.oscarg798.babyrecord.core.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by oscarg798 on 12/21/17.
 */
@Entity(tableName = "baby")
data class DBBaby(
    @PrimaryKey
    val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "birthDate")
    val birthDate: Long
)
