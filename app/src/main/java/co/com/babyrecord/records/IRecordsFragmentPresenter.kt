package co.com.babyrecord.records

import co.com.babyrecord.IBasePresenter
import co.com.core.models.Record
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog

/**
 * Created by oscarg798 on 12/20/17.
 */
interface IRecordsFragmentPresenter : IBasePresenter<IRecordsFragmentView>,
        IRecordCallbacks, DatePickerDialog.OnDateSetListener {


    fun creteRecord(viewID: Int)

    fun updateRecord(record: Record)

    fun getSleepRecordByDateFromRecord(record: Record): SleepRecordsByDate

}