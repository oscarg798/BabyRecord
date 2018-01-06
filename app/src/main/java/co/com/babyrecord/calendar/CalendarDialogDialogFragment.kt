package co.com.babyrecord.calendar


import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.babyrecord.R
import com.squareup.timessquare.CalendarCellDecorator
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.fragment_calendar_view.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class CalendarDialogDialogFragment : DialogFragment(), ICalendarDialogFragmentView {


    private val mPresenter: ICalendarDialogFragmentPresenter = CalendarDialogDialogFragmentPresenter()

    override fun initComponents() {
        mSRLCalendar?.isEnabled = false

    }

    override fun showProgressBar() {
        mSRLCalendar?.isRefreshing = true
    }

    override fun hideProgressBar() {
        mSRLCalendar?.isRefreshing = false
    }

    override fun initCalendar(minDate: Long, daysWithRecords: List<Date>) {
        val minDateCalendar = Calendar.getInstance()
        minDateCalendar.timeInMillis = minDate
        val maxDateCalendar = Calendar.getInstance()
        maxDateCalendar.timeInMillis = minDate
        maxDateCalendar.add(Calendar.YEAR, 1)
        val decorators = arrayListOf(CalendarDecorator())
        mCVRecords?.decorators = decorators as List<CalendarCellDecorator>?
        mCVRecords?.setCustomDayView(SampelDayViewAdapter())
        mCVRecords?.init(minDateCalendar.time, maxDateCalendar.time)?.inMode(CalendarPickerView.SelectionMode.SINGLE)
        mCVRecords?.setTypeface(ResourcesCompat.getFont(activity, R.font.roboto_medium))
       // mCVRecords?.highlightDates(arrayListOf(Calendar.getInstance().time))

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCVRecords?.init(Calendar.getInstance().time, Calendar.getInstance().time)
        mPresenter.bind(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar_view, container, false)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.unBind()
    }

    companion object {
        fun newInstance(): CalendarDialogDialogFragment = CalendarDialogDialogFragment()
    }

}
