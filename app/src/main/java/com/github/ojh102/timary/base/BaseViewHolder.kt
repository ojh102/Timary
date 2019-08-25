package com.github.ojh102.timary.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

internal open class BaseViewHolder<T : BaseItem>(open val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    open fun bind(item: T) {
        viewDataBinding.setVariable(BR.item, item)
        viewDataBinding.executePendingBindings()
    }
}
