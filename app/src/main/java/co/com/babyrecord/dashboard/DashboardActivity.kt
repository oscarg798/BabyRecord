package co.com.babyrecord.dashboard

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.com.babyrecord.R
import co.com.core.models.Record
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), IDashboardView {

    private val mPresenter: IDashbaordActivityPresenter = DashboardActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mPresenter.bind(this)

    }

    override fun initComponents() {
        mSRLDashboard?.isEnabled = false
        mRVRecords?.setHasFixedSize(false)
        mRVRecords?.layoutManager = LinearLayoutManager(this)
        mRVRecords?.adapter = RecordsRecyclerViewAdapter(ArrayList(), mPresenter)
        mFABCreateSpleepRecord?.setOnClickListener { v ->
            mPresenter.creteRecord(v.id)
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
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("Accept", okButtonCallback)
        builder.setNegativeButton("Cancel") { _, _ -> }
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unBind()
    }
}
