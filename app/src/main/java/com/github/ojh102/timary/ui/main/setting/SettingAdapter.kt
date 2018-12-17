package com.github.ojh102.timary.ui.main.setting

import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewSettingDeepLineBinding
import com.github.ojh102.timary.databinding.ViewSettingLineBinding
import com.github.ojh102.timary.databinding.ViewSettingSwitchBinding
import com.github.ojh102.timary.databinding.ViewSettingTitleBinding
import com.github.ojh102.timary.util.extension.inflater

class SettingAdapter : ListAdapter<SettingItems, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<SettingItems>() {

    override fun areItemsTheSame(oldItem: SettingItems, newItem: SettingItems): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SettingItems, newItem: SettingItems): Boolean {
        return oldItem.id == newItem.id
    }

}) {

    companion object {
        const val TYPE_SWITCH = 100
        const val TYPE_TITLE = 101
        const val TYPE_LINE = 102
        const val TYPE_DEEP_LINE = 103
    }

    interface Callbacks {
        fun onCheckedAlert(checked: Boolean)
        fun onClickTerm()
    }

    private var callbacks: Callbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_SWITCH -> {
                val viewHolder = SwitchViewHolder(ViewSettingSwitchBinding.inflate(parent.inflater(), parent, false))

                viewHolder.setOnCheckedChnageListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
                    val item = getItem(viewHolder.adapterPosition)

                    when(item){
                        is SettingItems.SwitchItem.Alert -> {
                            callbacks?.onCheckedAlert(isChecked)
                        }
                    }
                })

                return viewHolder
            }
            TYPE_TITLE -> {
                val viewHolder = TitleViewHolder(ViewSettingTitleBinding.inflate(parent.inflater(), parent, false))

                viewHolder.setOnClickListener(View.OnClickListener {
                    val item = getItem(viewHolder.adapterPosition)

                    when(item){
                        is SettingItems.TitleItem.Term -> {
                            callbacks?.onClickTerm()
                        }
                    }
                })

                return viewHolder
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SwitchViewHolder -> {
                holder.bind(getItem(position) as SettingItems.SwitchItem)
            }
            is TitleViewHolder -> {
                holder.bind(getItem(position) as SettingItems.TitleItem)
            }
            is LineViewHolder -> {
                holder.bind(getItem(position) as SettingItems.LineItem)
            }
            is DeepLineViewHolder -> {
                holder.bind(getItem(position) as SettingItems.DeepLineItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is SettingItems.SwitchItem -> {
                TYPE_SWITCH
            }
            is SettingItems.TitleItem -> {
                TYPE_TITLE
            }
            is SettingItems.LineItem -> {
                TYPE_LINE
            }
            is SettingItems.DeepLineItem -> {
                TYPE_DEEP_LINE
            }
        }
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }

}