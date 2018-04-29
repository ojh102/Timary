package com.github.ojh102.timary.ui.main.setting

import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewSettingDeepLineBinding
import com.github.ojh102.timary.databinding.ViewSettingLineBinding
import com.github.ojh102.timary.databinding.ViewSettingSwitchBinding
import com.github.ojh102.timary.databinding.ViewSettingTitleBinding

class SwitchViewHolder(private val binding: ViewSettingSwitchBinding) : BaseViewHolder<SwitchItem>(binding) {

    override fun bind(item: SwitchItem) {
        binding.switchItem = item
    }

}

class TitleViewHolder(private val binding: ViewSettingTitleBinding) : BaseViewHolder<TitleItem>(binding) {

    override fun bind(item: TitleItem) {
        binding.titleItem = item
    }

}

class LineViewHolder(private val binding: ViewSettingLineBinding) : BaseViewHolder<LineItem>(binding) {

    override fun bind(item: LineItem) {

    }

}

class DeepLineViewHolder(private val binding: ViewSettingDeepLineBinding) : BaseViewHolder<DeepLineItem>(binding) {

    override fun bind(item: DeepLineItem) {

    }

}