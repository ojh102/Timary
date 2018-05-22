package com.github.ojh102.timary.ui.main.home

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.github.ojh102.timary.base.BaseRecyclerViewAdapter
import com.github.ojh102.timary.databinding.ViewCapsuleCloseBinding
import com.github.ojh102.timary.databinding.ViewCapsuleOpenBinding
import com.github.ojh102.timary.databinding.ViewHomeHeaderBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.inflater

class HomeAdapter(
        private val timaryParser: TimaryParser
) : BaseRecyclerViewAdapter() {

    companion object {
        const val TYPE_UNKNOWN = -1
        const val TYPE_HEADER = 100
        const val TYPE_CLOSED_CAPSULE = 101
        const val TYPE_OPENED_CAPSULE = 102
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                HomeHeaderViewHolder(ViewHomeHeaderBinding.inflate(parent.inflater(), parent, false), timaryParser)
            }
            TYPE_CLOSED_CAPSULE -> {
                ClosedCapsuleViewHolder(ViewCapsuleCloseBinding.inflate(parent.inflater(), parent, false), timaryParser)
            }
            TYPE_OPENED_CAPSULE -> {
                OpenedCapsuleViewHolder(ViewCapsuleOpenBinding.inflate(parent.inflater(), parent, false), timaryParser)
            }
            else -> {
                throw IllegalArgumentException("is invalid view type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is HomeHeaderItem -> {
                TYPE_HEADER
            }
            is Capsule -> {
                if (item.isOpened()) {
                    TYPE_OPENED_CAPSULE
                } else {
                    TYPE_CLOSED_CAPSULE
                }
            }
            else -> {
                TYPE_UNKNOWN
            }
        }
    }

}