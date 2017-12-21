package co.com.core.use_cases.record

import co.com.core.models.Record
import co.com.core.use_cases.SingleUseCase
import co.com.data.persistence.PersistenceFactory
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 12/20/17.
 */
class GetRecordsUseCase(mSubcribeOnScheduler: Scheduler,
                        mObseverOnScheduler: Scheduler) :
        SingleUseCase<List<Record>, Any?>(mSubcribeOnScheduler, mObseverOnScheduler) {


    override fun buildUseCase(params: Any?): Single<List<Record>> {
        return Single.create { emitter ->
            emitter.onSuccess(PersistenceFactory.instance.getDatabase().recordDAO().getRecords()
                    .mapTo(ArrayList(), {
                        Record(it.uuid, it.startTime, it.endTime, it.type)
                    }))
        }
    }
}


