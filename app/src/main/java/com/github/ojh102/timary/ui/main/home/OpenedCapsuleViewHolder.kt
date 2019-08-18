package com.github.ojh102.timary.ui.main.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewCapsuleOpenBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.TimaryParser

class OpenedCapsuleViewHolder(
    private val binding: ViewCapsuleOpenBinding,
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
