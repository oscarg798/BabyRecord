package co.com.babyrecord.sleep_record

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.com.babyrecord.R
import co.com.babyrecord.Utils
import co.com.core.models.Record
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by oscarg798 on 12/20/17.
 */
class RecordsRecyclerViewAdapter(val mRecords: ArrayList<SleepRecordsByDate>,
                                 private val mCallbacks: IRecordCallbacks) :
        ExpandableRecyclerViewAdapter<SleepRecordHeaderViewholder, RecordViewHolder>(mRecords) {

    private val mCalendar = Calendar.getInstance()

    private val mCurrentDayInLong = Calendar.getInstance().timeInMillis

    private val mSimpleTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    private val mSimpleDateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_view_holder, parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindChildViewHolder(holder: RecordViewHolder?, flatPosition: Int, group: ExpandableGroup<*>?, childIndex: Int) {
        holder?.let {
            holder.mTVStartTime?.
                    setText(Utils.instance.
                            boldTextPrefix("Start Time: ${getTimeFromDate((group as SleepRecordsByDate).getItems().get(childIndex)
                                    .startTime)}"), TextView.BufferType.SPANNABLE)

            holder.mTVEndTime?.setText(Utils.instance.
                    boldTextPrefix(if ((group as SleepRecordsByDate).getItems().get(childIndex).endTime !== null)
                        "End Time: ${getTimeFromDate((group as SleepRecordsByDate).getItems().get(childIndex).endTime!!)}"
                    else "End Time:"), TextView.BufferType.SPANNABLE)


            holder.mTVDate?.
                    setText(Utils.instance.
                            boldTextPrefix("Date: ${getDate((group as SleepRecordsByDate).getItems().get(childIndex).startTime)}"),
                            TextView.BufferType.SPANNABLE)

            holder.mTVType?.
                    setText(Utils.instance.
                            boldTextPrefix("Type: ${(group as SleepRecordsByDate).getItems().get(childIndex).type}"),
                            TextView.BufferType.SPANNABLE)

            val duration = Utils.instance.calculateRecordDuration((group as SleepRecordsByDate).getItems().get(childIndex).startTime,
                    group.items[childIndex].endTime)
            if (duration !== null) {
                holder.mTVRecordDuration?.
                        setText(Utils.instance.
                                boldTextPrefix("Duration: $duration"),
                                TextView.BufferType.SPANNABLE)
                holder.mTVRecordDuration?.visibility = View.VISIBLE
            } else {
                holder.mTVRecordDuration?.visibility = View.GONE
            }


            if (childIndex - 1 >= 0) {
                val sleepDuration = Utils.instance.
                        calculateRecordDuration(group.items[childIndex - 1].endTime, group.items[childIndex].startTime)
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
                mCallbacks.finishRecord(group.items[childIndex])
            }

            holder.mTVDeleteRecord?.setOnClickListener {
                mCallbacks.deleteRecord(group.items[childIndex])
            }

            holder.mTVSetEndTimeToRecord?.visibility = if (Utils.instance.canStopTheRecord(group.items[childIndex].startTime,
                    mCurrentDayInLong)) View.VISIBLE else View.GONE

        }
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): SleepRecordHeaderViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sleep_record_header, parent, false)
        return SleepRecordHeaderViewholder(view)
    }

    override fun onBindGroupViewHolder(holder: SleepRecordHeaderViewholder?, flatPosition: Int, group: ExpandableGroup<*>?) {
        holder?.let {
            group?.let {
                holder.mTVTitle.text = group.title
            }
        }
    }


    private fun getTimeFromDate(time: Long): String {
        return mSimpleTimeFormat.format(Date(time))
    }

    private fun getDate(date: Long): String {
        return mSimpleDateFormat.format(Date(date))
    }


    fun addRecord(record: Record) {
        mCalendar.timeInMillis = record.startTime
        var hasFound = false
        for (sleepRecordByDate in mRecords) {
            if (sleepRecordByDate.mYear == mCalendar.get(Calendar.YEAR)
                    && sleepRecordByDate.mDayOfTheYear == mCalendar.get(Calendar.DAY_OF_YEAR)) {
                sleepRecordByDate.items.add(record)
                hasFound = true
                break
            }
        }
        if (!hasFound) {
            mRecords.add(SleepRecordsByDate(mSimpleDateFormat.format(Date(record.startTime)),
                    arrayListOf(record), mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.DAY_OF_YEAR)))
        }

        sort()

    }

    fun addRecords(records: ArrayList<SleepRecordsByDate>) {
        mRecords.addAll(records)
        sort()
    }

    fun updateRecord(record: Record) {
        loop@ for (sleepRecordByDate in mRecords) {
            for (sleepRecord in sleepRecordByDate.items) {
                if (sleepRecord.uuid == record.uuid) {
                    sleepRecordByDate.items.remove(sleepRecord)
                    sleepRecordByDate.items.add(record)
                    break@loop
                }
            }
        }
        sort()
    }

    private fun sort() {
        mRecords.sortByDescending { it.title }
        mRecords.forEach {
            it.items.sortBy { it.startTime }
        }
        notifyDataSetChanged()

    }

    fun removeRecord(recordUuid: String) {
        loop@ for (sleepRecordByDate in mRecords) {
            for (sleepRecord in sleepRecordByDate.items) {
                if (sleepRecord.uuid == recordUuid) {
                    sleepRecordByDate.items.remove(sleepRecord)
                    if (sleepRecordByDate.items.size == 0) {
                        mRecords.remove(sleepRecordByDate)
                    }
                    break@loop
                }
            }
        }
        sort()
    }
}