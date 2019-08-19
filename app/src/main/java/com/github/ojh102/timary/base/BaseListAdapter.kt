package com.github.ojh102.timary.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.BR

internal abstract class BaseListAdapter<T : BaseItem>(
    diffCallback: DiffUtil.ItemCallback<T> = BaseDiffItemCallback()
) : ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    protected var viewModel: ViewModel? = null

    private var itemClickListener: ((View, T) -> Unit)? = null

    abstract fun layoutIdByViewType(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)

        val binding = createDataBinding(parent, inflater, layoutIdByViewType(viewType))

        val viewHolder = createViewHolder(binding, viewType)

        binding.setVariable(BR.clickListener, View.OnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                itemClickListener?.invoke(it, getItem(viewHolder.adapterPosition))
            }
        })

        binding.setVariable(BR.viewModel, viewModel)

        onBindingAndHolderCreated(viewHolder, binding, viewType)

        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int, payloads: MutableList<Any>) {
        holder.bind(getItem(position))
    }

    open fun onBindingAndHolderCreated(viewHolder: BaseViewHolder<T>, binding: ViewDataBinding, viewType: Int) {}

    open fun createViewHolder(binding: ViewDataBinding, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(binding)
    }

    open fun createDataBinding(parent: ViewGroup, inflater: LayoutInflater, layoutIdByViewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(inflater, layoutIdByViewType, parent, false)
    }

    fun setCallbacks(viewModel: ViewModel) {
        this.viewModel = viewModel
    }

    fun setItemClickListener(itemClickListener: (View, T) -> Unit) {
        this.itemClickListener = itemClickListener
    }
}
