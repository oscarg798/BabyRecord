package co.com.babyrecord.dashboard

import android.content.DialogInterface
import co.com.babyrecord.IBaseView
import co.com.core.models.Record

/**
 * Created by oscarg798 on 12/20/17.
 */
interface IDashboardView : IBaseView {

    fun showRecords(records: List<Record>)

    fun updateRecord(record: Record)

    fun addRecord(record: Record)

    fun showConfirmationAlertDialog(message: String, okButtonCallback: DialogInterface.OnClickListener)

    fun removeRecord(recordUuid: String)

}