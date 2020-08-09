package com.oscarg798.babyrecord.records.usecases

import com.oscarg798.babyrecord.core.models.BabyRecordType
import com.oscarg798.babyrecord.core.models.Record
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetRecordsUseCase @Inject constructor() {

    suspend operator fun invoke() = flow<List<Record>> {
        delay(500)
        emit(
            listOf(
                Record("1", "2", BabyRecordType.getTypeFromName("sleep"), 3, 0),
                Record("2", "3", BabyRecordType.getTypeFromName("sleep"), 1596897906, 1596016369),
                Record("2", "3", BabyRecordType.getTypeFromName("medicine"), 1596897906, null)
            )
        )

    }
}
