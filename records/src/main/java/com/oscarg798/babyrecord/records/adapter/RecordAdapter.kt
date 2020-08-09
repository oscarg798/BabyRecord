package com.oscarg798.babyrecord.records.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.oscarg798.babyrecord.records.databinding.RecordItemBinding
import com.oscarg798.babyrecord.records.model.ViewRecord

class RecordAdapter : ListAdapter<ViewRecord, RecordViewHolder>(object : DiffUtil.ItemCallback<ViewRecord>() {
        override fun areItemsTheSame(oldItem: ViewRecord, newItem: ViewRecord): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ViewRecord, newItem: ViewRecord): Boolean =
            oldItem == newItem

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder =
        RecordViewHolder(
            RecordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
