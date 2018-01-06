package co.com.babyrecord.calendar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import co.com.babyrecord.R
import com.squareup.timessquare.CalendarCellDecorator
import com.squareup.timessquare.CalendarCellView
import java.util.*

/**
 * Created by oscarg798 on 1/5/18.
 */

class CalendarDecorator : CalendarCellDecorator {

    private val mCalendar = Calendar.getInstance()

    private val mCurrentCalendar = Calendar.getInstance()

    override fun decorate(cellView: CalendarCellView?, date: Date?) {
        cellView?.let {
            date?.let {

                val bgShape = cellView.findViewById<View>(R.id.mTVCalendarDay).background as? GradientDrawable

                bgShape?.let {
                    if (isTheSameDate(date) && cellView.isSelectable) {
                        bgShape.setColor(Color.GREEN)
                    } else {
                        bgShape.setColor(Color.WHITE)
                    }
                }
            }
        }
    }

    private fun isTheSameDate(date: Date): Boolean {
        mCalendar.time = date
        return mCalendar.get(Calendar.YEAR) == mCurrentCalendar.get(Calendar.YEAR)
                && mCalendar.get(Calendar.DAY_OF_YEAR) == mCurrentCalendar.get(Calendar.DAY_OF_YEAR)
    }
}