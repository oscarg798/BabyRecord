package co.com.babyrecord.calendar

import android.content.Intent
import co.com.core.use_cases.record.GetMinRecordDateUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by oscarg798 on 1/5/18.
 */
class CalendarDialogDialogFragmentPresenter : ICalendarDialogFragmentPresenter {

    private var mView: ICalendarDialogFragmentView? = null

    override fun bind(view: ICalendarDialogFragmentView) {
        mView = view
        mView?.initComponents()
        getMinAndMaxDate()
    }

    private fun getMinAndMaxDate() {
        mView?.showProgressBar()
        GetMinRecordDateUseCase(Schedulers.io(),
                AndroidSchedulers.mainThread())
                .execute(null, object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {
                        mView?.initCalendar(t, arrayListOf(Calendar.getInstance().time) )
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

    override fun unBind() {
        mView = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }
}