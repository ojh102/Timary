package com.github.ojh102.timary.ui.main.home

import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewHomeHeaderBinding
import com.github.ojh102.timary.util.TimaryParser

class HomeHeaderViewHolder(
    private val binding: ViewHomeHeaderBinding,
    timaryParser: TimaryParser
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.timaryParser = timaryParser
    }

    fun bind(item: HomeItems.Header) {
        binding.headerItem = item
    }
}
