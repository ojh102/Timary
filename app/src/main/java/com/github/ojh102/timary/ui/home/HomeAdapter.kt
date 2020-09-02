package com.github.ojh102.timary.ui.home

import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseListAdapter
import com.github.ojh102.timary.base.BaseViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

internal class HomeAdapter @Inject constructor(viewModel: BaseViewModel) : BaseListAdapter<HomeItems>(viewModel) {

    companion object {
        const val TYPE_HEADER = 100
        const val TYPE_CLOSED_CAPSULE = 101
        const val TYPE_OPENED_CAPSULE = 102
    }

    override fun layoutIdByViewType(viewType: Int): Int {
        return when (viewType) {
            TYPE_HEADER -> R.layout.item_home_header
            TYPE_CLOSED_CAPSULE -> R.layout.item_capsule_close
            TYPE_OPENED_CAPSULE -> R.layout.item_capsule_open
            else -> throw IllegalArgumentException("invalid viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
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
}
