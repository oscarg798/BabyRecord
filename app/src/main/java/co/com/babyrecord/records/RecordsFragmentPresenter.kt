package co.com.babyrecord.records

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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by oscarg798 on 12/20/17.
 */
class RecordsFragmentPresenter : IRecordsFragmentPresenter {

    private var mView: IRecordsFragmentView? = null

    private var mRecords: List<Record>? = null

    private val mCalendar = Calendar.getInstance()

    private val mSimpleDateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    private var mCurrentRecordsCalendar: Calendar = Calendar.getInstance()

    override fun bind(view: IRecordsFragmentView) {
        mView = view
        mView?.initComponents()
        if (mRecords === null) {
            getRecords(Calendar.getInstance().timeInMillis)
        } else {
            deliverRecords()
        }
        mView?.setRecordsDate(mSimpleDateFormat.format(mCurrentRecordsCalendar.time))

    }

    override fun unBind() {
        mView = null
    }

    private fun getRecords(date: Long) {
        mView?.showProgressBar()
        GetRecordsUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
                .execute(date, object : DisposableSingleObserver<List<Record>>() {
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
            mView?.showRecords(ArrayList(mRecords))
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

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mCurrentRecordsCalendar.set(year, monthOfYear, dayOfMonth)
        mView?.clearRecords()
        mView?.setRecordsDate(mSimpleDateFormat.format(mCurrentRecordsCalendar.time))
        getRecords(mCurrentRecordsCalendar.timeInMillis)
        val currentCalendar = Calendar.getInstance()
        mView?.showHideFabMenuCreateRecords(currentCalendar.get(Calendar.YEAR) == mCurrentRecordsCalendar.get(Calendar.YEAR) &&
                currentCalendar.get(Calendar.DAY_OF_YEAR) == mCurrentRecordsCalendar.get(Calendar.DAY_OF_YEAR))

    }

    override fun getSleepRecordByDateFromRecord(record: Record): SleepRecordsByDate {
        mCalendar.timeInMillis = record.startTime
        return SleepRecordsByDate(mSimpleDateFormat.format(Date(record.startTime)),
                arrayListOf(record), mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.DAY_OF_YEAR))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}