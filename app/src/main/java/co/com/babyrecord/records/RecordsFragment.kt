package co.com.babyrecord.records

import android.app.Fragment
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.babyrecord.DATE_PICKER_DIALOG_TAG
import co.com.babyrecord.R
import co.com.babyrecord.records.widget.RecordsWidget
import co.com.core.models.Record
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_sleep_record.*
import java.util.*
import kotlin.collections.ArrayList

class RecordsFragment : Fragment(), IRecordsFragmentView {

    private val mPresenter: IRecordsFragmentPresenter = RecordsFragmentPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_sleep_record, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.bind(this)

    }


    fun sendRefreshBroadcast() {
        activity?.let {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            intent.component = ComponentName(activity, RecordsWidget::class.java)
            activity.sendBroadcast(intent)
        }

    }

    override fun initComponents() {
        activity?.let {
            mSRLDashboard?.isEnabled = false
            mRVRecords?.setHasFixedSize(false)
            mRVRecords?.layoutManager = LinearLayoutManager(activity)
            mRVRecords?.adapter = RecordsRecyclerViewAdapter(ArrayList(), mPresenter)

            mFABCreateSleepRecord?.setOnClickListener { v ->
                mPresenter.creteRecord(v.id)
                mFAMRecord.collapse()
            }

            mFABCreateMedicineRecord?.setOnClickListener { v ->
                mPresenter.creteRecord(v.id)
                mFAMRecord.collapse()
            }

            mFABCreateFeedRecord.setOnClickListener { v ->
                mPresenter.creteRecord(v.id)
                mFAMRecord.collapse()
            }

            mLLRecordsDate?.setOnClickListener {
                val now = Calendar.getInstance()
                val dpd = DatePickerDialog.newInstance(
                        mPresenter,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                )
                dpd.maxDate = now
                now.add(Calendar.YEAR, -1)
                dpd.minDate = now

                dpd.show(fragmentManager, DATE_PICKER_DIALOG_TAG)
            }
        }
    }

    override fun showProgressBar() {
        mSRLDashboard?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLDashboard?.isRefreshing = false
    }

    override fun showRecords(records: ArrayList<Record>) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).addRecord(records)
        }
        sendRefreshBroadcast()

    }

    override fun updateRecord(record: Record) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).updateRecord(record)
        }
    }

    override fun addRecord(record: Record) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).addRecord(arrayListOf(record))

        }
    }

    override fun removeRecord(recordUuid: String) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).removeRecord(recordUuid)
        }
    }

    override fun showConfirmationAlertDialog(message: String, okButtonCallback: DialogInterface.OnClickListener) {
        activity?.let {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(message)
            builder.setPositiveButton(getString(R.string.accept_label), okButtonCallback)
            builder.setNegativeButton(getString(R.string.cancel_label)) { _, _ -> }
            builder.create().show()
        }

    }

    override fun showHideFabMenuCreateRecords(show: Boolean) {
        mFAMRecord?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun setRecordsDate(date: String) {
        mTVRecordsDate?.text = date
    }

    override fun clearRecords() {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unBind()
    }


    companion object {
        fun newInstance(): RecordsFragment = RecordsFragment()
    }
}
