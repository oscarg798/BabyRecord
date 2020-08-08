package com.oscarg798.babyrecord.core.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.oscarg798.babyrecord.core.models.BabyRecordType
import com.oscarg798.babyrecord.core.models.Record
import java.util.UUID

/**
 * Created by oscarg798 on 12/20/17.
 */
@Entity(
    tableName = "record", foreignKeys = [ForeignKey(
        entity = DBBaby::class,
        parentColumns = arrayOf("uuid"),
        childColumns = arrayOf("babyId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DBRecord(
    @PrimaryKey
    val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "babyId")
    val babyId: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "startTime")
    val startTime: Long,
    @ColumnInfo(name = "endTime")
    val endTime: Long? = null
) {

    fun toRecord() = Record(uuid, babyId, BabyRecordType.getTypeFromName(type), startTime, endTime)
}
