package co.com.babyrecord.dashboard

import co.com.babyrecord.IBasePresenter
import co.com.core.models.Record

/**
 * Created by oscarg798 on 12/20/17.
 */
interface ISleepRecordFragmentPresenter : IBasePresenter<ISleepRecordFragmentView>,
        IRecordCallbacks {


    fun creteRecord(viewID: Int)

    fun updateRecord(record: Record)

}