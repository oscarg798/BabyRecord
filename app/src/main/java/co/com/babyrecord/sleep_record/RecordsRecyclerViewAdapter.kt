package co.com.babyrecord.sleep_record

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.com.babyrecord.R
import co.com.babyrecord.Utils
import co.com.core.models.Record
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by oscarg798 on 12/20/17.
 */
class RecordsRecyclerViewAdapter(private val mRecords: ArrayList<Record>,
                                 private val mCallbacks: IRecordCallbacks) :
        RecyclerView.Adapter<RecordViewHolder>() {

    private val mSimpleTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    private val mSimpleDateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    override fun onBindViewHolder(holder: RecordViewHolder?, position: Int) {
        holder?.let {
            holder.mTVStartTime?.
                    setText(Utils.instance.
                            boldTextPrefix("Start Time: ${getTimeFromDate(mRecords[position]
                                    .startTime)}"), TextView.BufferType.SPANNABLE)

            holder.mTVEndTime?.setText(Utils.instance.
                    boldTextPrefix(if (mRecords[position].endTime !== null)
                        "End Time: ${getTimeFromDate(mRecords[position].endTime!!)}"
                    else "End Time:"), TextView.BufferType.SPANNABLE)


            holder.mTVDate?.
                    setText(Utils.instance.
                            boldTextPrefix("Date: ${getDate(mRecords[position].startTime)}"),
                            TextView.BufferType.SPANNABLE)

            holder.mTVType?.
                    setText(Utils.instance.
                            boldTextPrefix("Type: ${mRecords[position].type}"),
                            TextView.BufferType.SPANNABLE)

            val duration = Utils.instance.calculateRecordDuration(mRecords[position].startTime,
                    mRecords[position].endTime)
            if (duration !== null) {
                holder.mTVRecordDuration?.
                        setText(Utils.instance.
                                boldTextPrefix("Duration: $duration"),
                                TextView.BufferType.SPANNABLE)
                holder.mTVRecordDuration?.visibility = View.VISIBLE
            } else {
                holder.mTVRecordDuration?.visibility = View.GONE
            }


            if (position - 1 >= 0) {
                val sleepDuration = Utils.instance.
                        calculateRecordDuration(mRecords[position - 1].endTime, mRecords[position].startTime)
                if (sleepDuration !== null) {
                    holder.mTVTimeFromLastRecord?.
                            setText(Utils.instance.
                                    boldTextPrefix("Elapsed Time: $sleepDuration"),
                                    TextView.BufferType.SPANNABLE)
                    holder.mTVTimeFromLastRecord?.visibility = View.VISIBLE
                } else {
                    holder.mTVTimeFromLastRecord?.visibility = View.GONE
                }

            } else {
                holder.mTVTimeFromLastRecord?.visibility = View.GONE
            }

            holder.mTVSetEndTimeToRecord?.setOnClickListener {
                mCallbacks.finishRecord(mRecords[position])
            }

            holder.mTVDeleteRecord?.setOnClickListener {
                mCallbacks.deleteRecord(mRecords[position])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_view_holder, parent, false)
        return RecordViewHolder(view)
    }

    private fun getTimeFromDate(time: Long): String {
        return mSimpleTimeFormat.format(Date(time))
    }

    private fun getDate(date: Long): String {
        return mSimpleDateFormat.format(Date(date))
    }

    override fun getItemCount(): Int = mRecords.size

    fun addRecords(records: List<Record>) {
        mRecords.addAll(records)
        sort()
        notifyDataSetChanged()
    }

    fun updateRecor(record: Record) {
        mRecords.filter {
            it.uuid == record.uuid
        }.forEach {
            mRecords.remove(it)
            mRecords.add(record)
        }
        sort()
        notifyDataSetChanged()
    }

    fun sort() {
        mRecords.sortByDescending { it.startTime }
        mRecords.reverse()
    }

    fun removeRecord(recordUuid: String) {
        mRecords.filter { it.uuid == recordUuid }
                .forEach {
                    mRecords.remove(it)

                }
        sort()
        notifyDataSetChanged()
    }
}