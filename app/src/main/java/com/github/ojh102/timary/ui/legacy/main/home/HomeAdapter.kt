package com.github.ojh102.timary.ui.legacy.main.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ojh102.timary.databinding.ViewCapsuleCloseBinding
import com.github.ojh102.timary.databinding.ViewCapsuleOpenBinding
import com.github.ojh102.timary.databinding.ViewHomeHeaderBinding
import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.inflater
import javax.inject.Inject

internal class HomeAdapter @Inject constructor(private val timaryParser: TimaryParser) :
    ListAdapter<HomeItems, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<HomeItems>() {
        override fun areItemsTheSame(oldItem: HomeItems, newItem: HomeItems): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeItems, newItem: HomeItems): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }) {

    companion object {
        const val TYPE_HEADER = 100
        const val TYPE_CLOSED_CAPSULE = 101
        const val TYPE_OPENED_CAPSULE = 102
    }

    interface Callbacks {
        fun onClickOpenedCapsule(capsule: Capsule)
        fun onClickClosedCapsule(capsule: Capsule)
    }

    private var callbacks: Callbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                HomeHeaderViewHolder(ViewHomeHeaderBinding.inflate(parent.inflater(), parent, false), timaryParser)
            }
            TYPE_CLOSED_CAPSULE -> {
                val viewHolder = ClosedCapsuleViewHolder(ViewCapsuleCloseBinding.inflate(parent.inflater(), parent, false), timaryParser)

                viewHolder.setOnClickListener(View.OnClickListener {
                    val item = getItem(viewHolder.adapterPosition) as HomeItems.StoredCapsule.ClosedCapsule

                    callbacks?.onClickClosedCapsule(item.capsule)
                })

                viewHolder
            }
            TYPE_OPENED_CAPSULE -> {
                val viewHolder = OpenedCapsuleViewHolder(ViewCapsuleOpenBinding.inflate(parent.inflater(), parent, false), timaryParser)

                viewHolder.setOnClickListener(View.OnClickListener {
                    val item = getItem(viewHolder.adapterPosition)

                    if (item is HomeItems.StoredCapsule.OpenedCapsule) {
                        val capsule = item.capsule

                        callbacks?.onClickOpenedCapsule(capsule)
                    }
                })

                viewHolder
            }
            else -> {
                throw IllegalArgumentException("is invalid view type $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeHeaderViewHolder -> {
                holder.bind((getItem(position) as HomeItems.Header))
            }
            is ClosedCapsuleViewHolder -> {
                holder.bind((getItem(position) as HomeItems.StoredCapsule.ClosedCapsule).capsule)
            }
            is OpenedCapsuleViewHolder -> {
                holder.bind((getItem(position) as HomeItems.StoredCapsule.OpenedCapsule).capsule)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is HomeItems.Header -> {
                TYPE_HEADER
            }
            is HomeItems.StoredCapsule.OpenedCapsule -> {
                TYPE_OPENED_CAPSULE
            }
            is HomeItems.StoredCapsule.ClosedCapsule -> {
                TYPE_CLOSED_CAPSULE
            }
        }
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }
}
