package com.github.ojh102.timary.ui.main.setting

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewSettingTitleBinding
import com.github.ojh102.timary.util.extension.inflater

class SettingAdapter : ListAdapter<SettingItems, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<SettingItems>() {

    override fun areItemsTheSame(oldItem: SettingItems, newItem: SettingItems): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SettingItems, newItem: SettingItems): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}) {

    companion object {
        const val TYPE_TITLE = 100
    }

    interface Callbacks {
        fun onClickTerm()
    }

    private var callbacks: Callbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_TITLE -> {
                val viewHolder = TitleViewHolder(
                    ViewSettingTitleBinding.inflate(
                        parent.inflater(),
                        parent,
                        false
                    )
                )

                viewHolder.setOnClickListener(View.OnClickListener {
                    val item = getItem(viewHolder.adapterPosition)

                    when (item) {
                        is SettingItems.TitleItem.Term -> {
                            callbacks?.onClickTerm()
                        }
                    }
                })

                return viewHolder
            }
            else -> {
                throw IllegalArgumentException("is invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> {
                holder.bind(getItem(position) as SettingItems.TitleItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SettingItems.TitleItem -> {
                TYPE_TITLE
            }
        }
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }
}
