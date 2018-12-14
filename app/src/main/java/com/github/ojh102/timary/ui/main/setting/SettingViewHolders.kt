package com.github.ojh102.timary.ui.main.setting

import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewSettingDeepLineBinding
import com.github.ojh102.timary.databinding.ViewSettingLineBinding
import com.github.ojh102.timary.databinding.ViewSettingSwitchBinding
import com.github.ojh102.timary.databinding.ViewSettingTitleBinding

class SwitchViewHolder(private val binding: ViewSettingSwitchBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingItems.SwitchItem) {
        binding.switchItem = item
    }

}

class TitleViewHolder(private val binding: ViewSettingTitleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingItems.TitleItem) {
        binding.titleItem = item
    }

}

class LineViewHolder(private val binding: ViewSettingLineBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingItems.LineItem) {

    }

}

class DeepLineViewHolder(private val binding: ViewSettingDeepLineBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingItems.DeepLineItem) {

    }

}