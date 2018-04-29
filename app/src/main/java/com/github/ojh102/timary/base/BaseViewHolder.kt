package com.github.ojh102.timary.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseViewHolder<in ITEM : Any>(
        binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    @Suppress("UNCHECKED_CAST")
    fun onBindViewHolder(item: Any?) {
        (item as? ITEM)?.let { bind(it) }
    }

    abstract fun bind(item: ITEM)
}