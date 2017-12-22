package co.com.babyrecord.sleep_record

import android.content.DialogInterface
import co.com.babyrecord.IBaseView
import co.com.core.models.Record

/**
 * Created by oscarg798 on 12/20/17.
 */
interface ISleepRecordFragmentView : IBaseView {

    fun showRecords(records: ArrayList<SleepRecordsByDate>)

    fun updateRecord(record: Record)

    fun addRecord(record: Record)

    fun showConfirmationAlertDialog(message: String, okButtonCallback: DialogInterface.OnClickListener)

    fun removeRecord(recordUuid: String)

}