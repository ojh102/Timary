package com.github.ojh102.timary.base

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val items = mutableListOf<Any>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.adapterPosition != RecyclerView.NO_POSITION) {
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