package com.oscarg798.babyrecord.core.repository

import com.oscarg798.babyrecord.core.models.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getRecords(): Flow<Collection<Record>>
}
