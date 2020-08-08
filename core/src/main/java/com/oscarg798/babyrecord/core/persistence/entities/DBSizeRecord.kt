package com.oscarg798.babyrecord.core.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by oscarg798 on 12/22/17.
 */
@Entity(
    tableName = "size_record", foreignKeys = [ForeignKey(
        entity = DBBaby::class,
        parentColumns = arrayOf("uuid"),
        childColumns = arrayOf("babyId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DBSizeRecord(
    @PrimaryKey
    val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "babyId")
    val babyId: String,
    @ColumnInfo(name = "height")
    val height: Float,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "measureDate")
    val measureDate: Long
)
