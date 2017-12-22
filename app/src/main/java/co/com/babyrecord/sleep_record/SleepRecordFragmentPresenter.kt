package co.com.babyrecord.dashboard

import android.content.DialogInterface
import co.com.babyrecord.BaseApplication
import co.com.babyrecord.R
import co.com.babyrecord.SLEEP_TYPE
import co.com.core.models.Record
import co.com.core.use_cases.record.CreateRecordUseCase
import co.com.core.use_cases.record.DeleteRecordUseCase
import co.com.core.use_cases.record.GetRecordsUseCase
import co.com.core.use_cases.record.UpdateRecordUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by oscarg798 on 12/20/17.
 */
class SleepRecordFragmentPresenter : ISleepRecordFragmentPresenter {

    private var mView: ISleepRecordFragmentView? = null

    private var mRecords: List<Record>? = null

    override fun bind(view: ISleepRecordFragmentView) {
        mView = view
        mView?.initComponents()
        if (mRecords === null) {
            getRecords()
        } else {
            deliverRecords()
        }
    }

    override fun unBind() {
        mView = null
    }

    private fun getRecords() {
        mView?.showProgressBar()
        GetRecordsUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
                .execute(null, object : DisposableSingleObserver<List<Record>>() {
                    override fun onSuccess(t: List<Record>) {
                        mRecords = t
                        deliverRecords()
                        mView?.hideProgressBar()
                        this.dispose()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        mView?.hideProgressBar()
                        this.dispose()
                    }
                })

    }

    private fun deliverRecords() {
        mRecords?.let {
            mView?.showRecords(mRecords!!)
        }
    }

    override fun creteRecord(viewID: Int) {
        mView?.showProgressBar()
        CreateRecordUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread()).
                execute(Record(UUID.randomUUID().toString(),
                        Calendar.getInstance().timeInMillis,
                        null, SLEEP_TYPE),
                        object : DisposableSingleObserver<Record>() {
                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                                mView?.hideProgressBar()
                                this.dispose()
                            }

                            override fun onSuccess(t: Record) {
                                mView?.addRecord(t)
                                mView?.hideProgressBar()
                                this.dispose()

                            }
                        })
    }

    override fun updateRecord(record: Record) {
        UpdateRecordUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
                .execute(record, object : DisposableSingleObserver<Record>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        mView?.hideProgressBar()
                        this.dispose()
                    }

                    override fun onSuccess(t: Record) {
                        mView?.updateRecord(record)
                        mView?.hideProgressBar()
                        this.dispose()
                    }
                })


    }

    override fun finishRecord(record: Record) {
        record.endTime = Calendar.getInstance().timeInMillis
        updateRecord(record)
    }

    override fun deleteRecord(record: Record) {

        mView?.showConfirmationAlertDialog(BaseApplication.instance.getString(R.string.delete_record_confirmation_message),
                DialogInterface.OnClickListener { _, _ ->
                    mView?.showProgressBar()
                    DeleteRecordUseCase(Schedulers.io(),
                            AndroidSchedulers.mainThread())
                            .execute(record, object : DisposableSingleObserver<String>() {
                                override fun onError(e: Throwable) {
                                    e.printStackTrace()
                                    mView?.hideProgressBar()
                                    this.dispose()
                                }

                                override fun onSuccess(t: String) {
                                    mView?.removeRecord(t)
                                    mView?.hideProgressBar()
                                    this.dispose()
                                }

                            })


                })

    }
}