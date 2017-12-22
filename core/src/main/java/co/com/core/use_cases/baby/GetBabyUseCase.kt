package co.com.core.use_cases.baby

import co.com.core.models.Baby
import co.com.core.use_cases.SingleUseCase
import co.com.data.persistence.PersistenceFactory
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 12/21/17.
 */
class GetBabyUseCase(mSubscribeOnScheduler: Scheduler,
                     mObserverOnScheduler: Scheduler) :
        SingleUseCase<Baby, Any?>(mSubscribeOnScheduler, mObserverOnScheduler) {


    override fun buildUseCase(params: Any?): Single<Baby> {
        return Single.create { emitter ->
            val dbBaby = PersistenceFactory.instance.getDatabase().babyDAO().get()
            emitter.onSuccess(Baby(dbBaby.name, dbBaby.birthDate, dbBaby.weight,
                    dbBaby.height))
        }
    }
}