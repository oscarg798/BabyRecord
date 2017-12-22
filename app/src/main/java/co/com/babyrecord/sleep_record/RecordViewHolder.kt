package co.com.babyrecord.sleep_record

import android.view.View
import android.widget.TextView
import co.com.babyrecord.R
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

/**
 * Created by oscarg798 on 12/20/17.
 */
class RecordViewHolder(mItemView: View) : ChildViewHolder(mItemView) {

    val mTVDate = mItemView.findViewById<TextView>(R.id.mTVDate)
    val mTVStartTime = mItemView.findViewById<TextView>(R.id.mTVStartTime)
    val mTVEndTime = mItemView.findViewById<TextView>(R.id.mTVEndTime)
    val mTVType = mItemView.findViewById<TextView>(R.id.mTVType)
    val mTVDeleteRecord = mItemView.findViewById<TextView>(R.id.mTVDeleteRecord)
    val mTVSetEndTimeToRecord = mItemView.findViewById<TextView>(R.id.mTVSetEndTimeToRecord)
    val mTVRecordDuration = mItemView.findViewById<TextView>(R.id.mTVRecordDuration)
    val mTVTimeFromLastRecord = mItemView.findViewById<TextView>(R.id.mTVTimeFromLastRecord)
}