package com.oscarg798.babyrecord.records.adapter

import androidx.recyclerview.widget.RecyclerView
import com.oscarg798.babyrecord.records.databinding.RecordItemBinding
import com.oscarg798.babyrecord.records.model.ViewRecord

class RecordViewHolder(private val binding: RecordItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewRecord: ViewRecord) {
        with(binding) {
            ivRecordIcon.setImageDrawable(itemView.context.getDrawable(viewRecord.iconId))
            tvRecordStartTime.text = viewRecord.startTime
            tvRecordEndTime.text = viewRecord.endTime ?: "--"
        }
    }

}
