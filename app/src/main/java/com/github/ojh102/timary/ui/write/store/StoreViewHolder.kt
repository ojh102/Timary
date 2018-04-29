package com.github.ojh102.timary.ui.write.store

import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewStoreBinding

class StoreViewHolder(private val binding: ViewStoreBinding): BaseViewHolder<StoreItem>(binding) {

    interface OnItemSelectedListener {
        fun onItemSelect(item: StoreItem, position: Int)
    }

    override fun bind(item: StoreItem) {
        binding.storeItem = item
        binding.position = adapterPosition
    }

}