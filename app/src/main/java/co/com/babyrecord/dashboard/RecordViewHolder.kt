package co.com.babyrecord.dashboard

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.com.babyrecord.R

/**
 * Created by oscarg798 on 12/20/17.
 */
class RecordViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {

    val mTVDate = mItemView.findViewById<TextView>(R.id.mTVDate)
    val mTVStartTime = mItemView.findViewById<TextView>(R.id.mTVStartTime)
    val mTVEndTime = mItemView.findViewById<TextView>(R.id.mTVEndTime)
    val mTVType = mItemView.findViewById<TextView>(R.id.mTVType)
    val mIVEndRecord = mItemView.findViewById<ImageView>(R.id.mIVEndRecord)
    val mIVDeleteRecord = mItemView.findViewById<ImageView>(R.id.mIVDeleteRecord)
}