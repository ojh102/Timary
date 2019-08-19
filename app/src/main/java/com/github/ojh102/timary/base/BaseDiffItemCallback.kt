package com.github.ojh102.timary.base

import androidx.recyclerview.widget.DiffUtil

internal open class BaseDiffItemCallback<T : BaseItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
