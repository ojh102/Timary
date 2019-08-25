package com.github.ojh102.timary.ui.legacy.main.setting

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewSettingTitleBinding

class TitleViewHolder(private val binding: ViewSettingTitleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingItems.TitleItem) {
        binding.titleItem = item
    }

    fun setOnClickListener(clickListener: View.OnClickListener) {
        binding.onClickListener = clickListener
    }
}