package com.github.ojh102.timary.ui.write.store

import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseListAdapter

internal class StoreAdapter(viewModel: StoreViewModel) : BaseListAdapter<StoreItems>(viewModel) {
    override fun layoutIdByViewType(viewType: Int): Int {
        return R.layout.view_store
    }
}
