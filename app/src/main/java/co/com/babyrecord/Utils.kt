package co.com.babyrecord

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import co.com.core.models.Record


/**
 * Created by oscarg798 on 12/21/17.
 */
class Utils private constructor() {

    fun calculateRecordDuration(startTime: Long?, endTime: Long?): String? {

        endTime?.let {
            startTime?.let {
                var different = endTime - startTime

                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60

                val elapsedHours = different / hoursInMilli
                different %= hoursInMilli

                val elapsedMinutes = different / minutesInMilli
                different %= minutesInMilli



                return if (elapsedHours > 0)
                    "$elapsedHours Hours and $elapsedMinutes minutes"
                else "$elapsedMinutes minutes"
            }


        }

        return null
    }

    fun boldTextPrefix(text: String): SpannableString {

        val spannable = SpannableString(text)
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), 0, text.indexOf(":") + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }

    private object Holder {

        val INSTANCE = Utils()
    }

    companion object {
        val instance by lazy {
            Holder.INSTANCE
        }
    }

}