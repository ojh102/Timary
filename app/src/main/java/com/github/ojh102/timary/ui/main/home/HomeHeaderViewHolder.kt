package com.github.ojh102.timary.ui.main.home

import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewHomeHeaderBinding

class HomeHeaderViewHolder(private val binding: ViewHomeHeaderBinding) : BaseViewHolder<HomeHeaderItem>(binding) {

    override fun bind(item: HomeHeaderItem) {
        binding.headerItem = item
    }

}