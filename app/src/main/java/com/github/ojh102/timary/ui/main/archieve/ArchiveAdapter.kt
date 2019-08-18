package com.github.ojh102.timary.ui.main.archieve

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.ojh102.timary.databinding.ViewCapsuleArchiveBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.inflater

class ArchiveAdapter(
    private val timaryParser: TimaryParser
) : ListAdapter<Capsule, ArchiveCapsuleViewHolder>(object : DiffUtil.ItemCallback<Capsule>() {

    override fun areItemsTheSame(oldItem: Capsule, newItem: Capsule): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Capsule, newItem: Capsule): Boolean {
        return oldItem.id == newItem.id
    }
}) {

    interface Callbacks {
        fun onClickArchiveCapsule(capsule: Capsule)
    }

    private var callbacks: ArchiveAdapter.Callbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveCapsuleViewHolder {
        val viewHolder = ArchiveCapsuleViewHolder(
                binding = ViewCapsuleArchiveBinding.inflate(parent.inflater(), parent, false),
                timaryParser = timaryParser
        )

        viewHolder.setOnClickListener(View.OnClickListener {
            val item = getItem(viewHolder.adapterPosition)

            callbacks?.onClickArchiveCapsule(item)
        })

        return viewHolder
    }

    override fun onBindViewHolder(holder: ArchiveCapsuleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }
}
