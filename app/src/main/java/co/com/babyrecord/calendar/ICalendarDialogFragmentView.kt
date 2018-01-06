package co.com.babyrecord.calendar

import co.com.babyrecord.IBaseView
import java.util.*

/**
 * Created by oscarg798 on 1/5/18.
 */
interface ICalendarDialogFragmentView : IBaseView {

    fun initCalendar(minDate: Long, daysWithRecords: List<Date>)


}
