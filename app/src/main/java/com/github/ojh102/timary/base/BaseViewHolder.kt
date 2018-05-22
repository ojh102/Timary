package com.github.ojh102.timary.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in ITEM : Any>(
        binding: ViewDataBinding
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    @Suppress("UNCHECKED_CAST")
    fun onBindViewHolder(item: Any?) {
        (item as? ITEM)?.let { bind(it) }
    }

    abstract fun bind(item: ITEM)
}