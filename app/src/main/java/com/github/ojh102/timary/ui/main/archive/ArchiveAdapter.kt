package com.github.ojh102.timary.ui.main.archive

import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseListAdapter
import com.github.ojh102.timary.base.BaseViewModel

internal class ArchiveAdapter(viewModel: BaseViewModel) : BaseListAdapter<ArchiveItems>(viewModel) {
    override fun layoutIdByViewType(viewType: Int): Int {
        return R.layout.view_capsule_archive
    }
}
