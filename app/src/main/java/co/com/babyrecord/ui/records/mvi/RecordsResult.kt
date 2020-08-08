package co.com.babyrecord.ui.records.mvi

import co.com.babyrecord.ui.records.failures.RecordsFailure
import com.oscarg798.babyrecord.core.base.Result
import com.oscarg798.babyrecord.core.models.Record

sealed class RecordsResult: Result {

    object Loading: RecordsResult()
    data class RecordsFound(val records: Collection<Record>): RecordsResult()
    data class Error(val failure: RecordsFailure): RecordsResult()

}
