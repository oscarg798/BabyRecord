package co.com.babyrecord.size_record


import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.babyrecord.CREAtE_SIZE_RECORD_REQUEST_CODE

import co.com.babyrecord.R
import co.com.babyrecord.add_size_record.AddSizeRecordActivity
import co.com.core.models.SizeRecord
import kotlinx.android.synthetic.main.activity_add_size_record.*
import kotlinx.android.synthetic.main.fragment_size_record.*


/**
 * A simple [Fragment] subclass.
 */
class SizeRecordFragment : Fragment(), ISizeRecordFragmentView {


    private val mPresenter: ISizeRecordFragmentPresenter = SizeRecordFragmentPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_size_record, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.bind(this)
    }

    override fun initComponents() {
        mSRLAddSizeRecord?.isEnabled = false
        mRVSizeRecords?.setHasFixedSize(false)
        mFABCreateSizeRecord?.setOnClickListener {
            activity?.let {
                startActivityForResult(Intent(activity, AddSizeRecordActivity::class.java),
                        CREAtE_SIZE_RECORD_REQUEST_CODE)
            }

        }

        activity?.let {
            mRVSizeRecords?.layoutManager = LinearLayoutManager(activity)
            mRVSizeRecords?.adapter = SizeRecordAdapter(ArrayList(), mPresenter)
        }

    }



    override fun showProgressBar() {
        mSRLAddSizeRecord?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLAddSizeRecord?.isRefreshing = false
    }

    override fun showSizeRecords(sizeRecords: ArrayList<SizeRecord>) {
        mRVSizeRecords?.adapter?.let {
            (mRVSizeRecords.adapter as SizeRecordAdapter).addSizeRecords(sizeRecords)
        }
    }

    override fun removeSizeRecord(sizeRecordUuid: String) {
        mRVSizeRecords?.adapter?.let {
            (mRVSizeRecords.adapter as SizeRecordAdapter).deleteRecord(sizeRecordUuid)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        fun newInstance(): SizeRecordFragment {
            return SizeRecordFragment()
        }
    }
}
