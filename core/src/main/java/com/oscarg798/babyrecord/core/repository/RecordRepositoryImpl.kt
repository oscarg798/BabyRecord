package com.oscarg798.babyrecord.core.repository

import com.oscarg798.babyrecord.core.models.Record
import com.oscarg798.babyrecord.core.persistence.dao.RecordDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordRepositoryImpl @Inject constructor(private val recordDAO: RecordDAO) : RecordRepository {

    override fun getRecords(): Flow<Collection<Record>> = recordDAO.getRecords().map {
        it.map { dbRecord -> dbRecord.toRecord() }
    }
}

