package com.github.ojh102.timary.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    protected val items = mutableListOf<Any>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        if(holder.adapterPosition != androidx.recyclerview.widget.RecyclerView.NO_POSITION) {
            (holder as? BaseViewHolder<*>)?.onBindViewHolder(items[position])
        }
    }

    fun setItems(items: List<Any>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: Any) {
        val insertPosition = items.size
        this.items.add(item)
        notifyItemInserted(insertPosition)
    }

    fun addItems(items: List<Any>) {
        val insertPosition = items.size
        this.items.addAll(items)
        notifyItemInserted(insertPosition)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}