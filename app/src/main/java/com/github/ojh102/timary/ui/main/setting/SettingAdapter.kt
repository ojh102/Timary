package com.github.ojh102.timary.ui.main.setting

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.ojh102.timary.base.BaseRecyclerViewAdapter
import com.github.ojh102.timary.databinding.ViewSettingDeepLineBinding
import com.github.ojh102.timary.databinding.ViewSettingLineBinding
import com.github.ojh102.timary.databinding.ViewSettingSwitchBinding
import com.github.ojh102.timary.databinding.ViewSettingTitleBinding
import com.github.ojh102.timary.util.extension.inflater

class SettingAdapter : BaseRecyclerViewAdapter() {

    companion object {
        const val TYPE_SWITCH = 100
        const val TYPE_TITLE = 101
        const val TYPE_LINE = 102
        const val TYPE_DEEP_LINE = 103
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_SWITCH -> {
                return SwitchViewHolder(ViewSettingSwitchBinding.inflate(parent.inflater(), parent, false))
            }
            TYPE_TITLE -> {
                return TitleViewHolder(ViewSettingTitleBinding.inflate(parent.inflater(), parent, false))
            }
            TYPE_LINE -> {
                return LineViewHolder(ViewSettingLineBinding.inflate(parent.inflater(), parent, false))
            }
            TYPE_DEEP_LINE -> {
                return DeepLineViewHolder(ViewSettingDeepLineBinding.inflate(parent.inflater(), parent, false))
            }
            else -> {
                throw IllegalArgumentException("is invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(items[position]) {
            is SwitchItem -> {
                return TYPE_SWITCH
            }
            is TitleItem -> {
                return TYPE_TITLE
            }
            is LineItem -> {
                return TYPE_LINE
            }
            is DeepLineItem -> {
                return TYPE_DEEP_LINE
            }
            else -> {
                return -1
            }
        }
    }

}