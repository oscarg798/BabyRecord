package com.oscarg798.babyrecord.core.usecase

import com.oscarg798.babyrecord.core.models.BabyRecordType
import com.oscarg798.babyrecord.core.models.Record
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ActivityScoped
class GetRecordsUseCase @Inject constructor() {

    suspend operator fun invoke() = flowOf(
        listOf(
            Record("1", "2", BabyRecordType.getTypeFromName("sleep"), 3, null),
            Record("2", "3", BabyRecordType.getTypeFromName("sleep"), 1596897906, 1596016369),
            Record("2", "3", BabyRecordType.getTypeFromName("medicine"), 1596897906, 1596016369)
        )
    )
}
