package com.github.ojh102.timary.ui.main.archieve

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.ojh102.timary.base.BaseRecyclerViewAdapter
import com.github.ojh102.timary.databinding.ViewCapsuleArchiveBinding
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.inflater

class ArchiveAdapter(
        private val timaryParser: TimaryParser
) : BaseRecyclerViewAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArchiveCapsuleViewHolder(
                binding = ViewCapsuleArchiveBinding.inflate(parent.inflater(), parent, false),
                timaryParser = timaryParser
        )
    }

}