package co.com.babyrecord.sleep_record

import android.content.DialogInterface
import android.content.Intent
import co.com.babyrecord.BaseApplication
import co.com.babyrecord.R
import co.com.babyrecord.SLEEP_TYPE
import co.com.core.models.Record
import co.com.core.use_cases.record.CreateRecordUseCase
import co.com.core.use_cases.record.DeleteRecordUseCase
import co.com.core.use_cases.record.GetRecordsUseCase
import co.com.core.use_cases.record.UpdateRecordUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by oscarg798 on 12/20/17.
 */
class SleepRecordFragmentPresenter : ISleepRecordFragmentPresenter {

    private var mView: ISleepRecordFragmentView? = null

    private var mRecords: List<Record>? = null

    private val mCalendar = Calendar.getInstance()

    private val mSimpleDateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

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
            mView?.showProgressBar()
            Single.create<ArrayList<SleepRecordsByDate>> { emitter ->
                val calendarMap = HashMap<Pair<Int, Int>, ArrayList<Record>>()
                mRecords!!.forEach {
                    mCalendar.timeInMillis = it.startTime
                    val recordsByDate = if (calendarMap.containsKey(Pair(mCalendar.get(Calendar.YEAR), mCalendar
                            .get(Calendar.DAY_OF_YEAR)))) {
                        calendarMap[Pair(mCalendar.get(Calendar.YEAR), mCalendar
                                .get(Calendar.DAY_OF_YEAR))]
                    } else {
                        ArrayList()
                    }

                    recordsByDate!!.add(it)
                    calendarMap.put(Pair(mCalendar.get(Calendar.YEAR), mCalendar
                            .get(Calendar.DAY_OF_YEAR)), recordsByDate)
                }

                val sleepRecordsByDate = ArrayList<SleepRecordsByDate>()

                calendarMap.mapTo(sleepRecordsByDate, {
                    mCalendar.timeInMillis = it.value[0].startTime
                    SleepRecordsByDate(mSimpleDateFormat.format(it.value[0].startTime), it.value,
                            mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.DAY_OF_YEAR))
                })
                emitter.onSuccess(sleepRecordsByDate)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableSingleObserver<ArrayList<SleepRecordsByDate>>() {
                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            mView?.hideProgressBar()
                            this.dispose()

                        }

                        override fun onSuccess(t: ArrayList<SleepRecordsByDate>) {
                            mView?.showRecords(t)
                            mView?.hideProgressBar()
                            this.dispose()
                        }

                    })
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}