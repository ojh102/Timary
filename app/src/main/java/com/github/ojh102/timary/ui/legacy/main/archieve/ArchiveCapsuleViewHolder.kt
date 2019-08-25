package com.github.ojh102.timary.ui.legacy.main.archieve

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewCapsuleArchiveBinding
import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.util.TimaryParser

internal class ArchiveCapsuleViewHolder(
    private val binding: ViewCapsuleArchiveBinding,
    timaryParser: TimaryParser
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.timaryParser = timaryParser
    }

    fun bind(item: Capsule) {
        binding.capsule = item
    }

    fun setOnClickListener(clickListener: View.OnClickListener) {
        binding.clickListener = clickListener
    }
}
