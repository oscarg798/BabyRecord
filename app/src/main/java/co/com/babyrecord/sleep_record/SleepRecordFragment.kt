package co.com.babyrecord.sleep_record

import android.app.Fragment
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.babyrecord.R
import co.com.core.models.Record
import kotlinx.android.synthetic.main.fragment_sleep_record.*

class SleepRecordFragment : Fragment(), ISleepRecordFragmentView {

    private val mPresenter: ISleepRecordFragmentPresenter = SleepRecordFragmentPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_sleep_record, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.bind(this)
    }


    override fun initComponents() {
        activity?.let {
            mSRLDashboard?.isEnabled = false
            mRVRecords?.setHasFixedSize(false)
            mRVRecords?.layoutManager = LinearLayoutManager(activity)
            mRVRecords?.adapter = RecordsRecyclerViewAdapter(ArrayList(), mPresenter)
            mFABCreateSpleepRecord?.setOnClickListener { v ->
                mPresenter.creteRecord(v.id)
            }
        }
    }

    override fun showProgressBar() {
        mSRLDashboard?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLDashboard?.isRefreshing = false
    }

    override fun showRecords(records: List<Record>) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).addRecords(records)
        }
    }

    override fun updateRecord(record: Record) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).updateRecor(record)
        }
    }

    override fun addRecord(record: Record) {
        mRVRecords?.adapter?.let {
            (mRVRecords.adapter as RecordsRecyclerViewAdapter).addRecords(listOf(record))
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

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unBind()
    }


    companion object {
        fun newInstance(): SleepRecordFragment = SleepRecordFragment()
    }
}
