package com.github.ojh102.timary.ui.main.home

import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewHomeHeaderBinding
import com.github.ojh102.timary.util.TimaryParser

class HomeHeaderViewHolder(
        private val binding: ViewHomeHeaderBinding,
        timaryParser: TimaryParser
) : BaseViewHolder<HomeHeaderItem>(binding) {

    init {
        binding.timaryParser = timaryParser
    }

    override fun bind(item: HomeHeaderItem) {
        binding.headerItem = item
    }

}