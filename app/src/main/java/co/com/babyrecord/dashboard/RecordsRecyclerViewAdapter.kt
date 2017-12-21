package co.com.babyrecord.dashboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.babyrecord.R
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
            holder.mTVStartTime.text = "Start Time: ${getTimeFromDate(mRecords[position].startTime)}"
            holder.mTVEndTime.text = if (mRecords[position].endTime !== null)
                "End Time: ${getTimeFromDate(mRecords[position].endTime!!)}" else "End Time:"
            holder.mTVDate.text = "Date: ${getDate(mRecords[position].startTime)}"
            holder.mTVType.text = "Type: ${mRecords[position].type}"
            holder.mIVEndRecord.visibility = if (mRecords[position].endTime === null) View.VISIBLE
            else View.GONE
            holder.mIVEndRecord.setOnClickListener {
                mCallbacks.finishRecord(mRecords[position])
            }
            holder.mIVDeleteRecord.setOnClickListener {
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
        notifyDataSetChanged()
    }

    fun updateRecor(record: Record) {
        mRecords.filter {
            it.uuid == record.uuid
        }.forEach {
            mRecords.remove(it)
            mRecords.add(record)
        }

        notifyDataSetChanged()
    }

    fun removeRecord(recordUuid: String) {
        mRecords.filter { it.uuid == recordUuid }
                .forEach {
                    mRecords.remove(it)

                }

        notifyDataSetChanged()
    }
}