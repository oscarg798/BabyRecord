package co.com.babyrecord.calendar

import android.view.LayoutInflater
import co.com.babyrecord.R
import com.squareup.timessquare.CalendarCellView
import com.squareup.timessquare.DayViewAdapter


/**
 * Created by oscarg798 on 1/5/18.
 */
class SampelDayViewAdapter : DayViewAdapter {

    override fun makeCellView(parent: CalendarCellView?) {
        parent?.let {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_calendar_view, parent, false)
            parent.addView(view)
            parent.dayOfMonthTextView = parent.findViewById(R.id.mTVCalendarDay)

        }

    }
}