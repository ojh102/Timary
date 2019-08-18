package com.github.ojh102.timary.ui.write.store

import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewStoreBinding

class StoreViewHolder(private val binding: ViewStoreBinding) : RecyclerView.ViewHolder(binding.root) {

    interface OnItemSelectedListener {
        fun onItemSelect(item: StoreItem, position: Int)
    }

    fun bind(item: StoreItem) {
        binding.storeItem = item
        binding.position = adapterPosition
    }
}
