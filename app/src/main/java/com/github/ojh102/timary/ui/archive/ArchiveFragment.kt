package com.github.ojh102.timary.ui.archive

import android.os.Bundle
import androidx.lifecycle.observe
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentArchiveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ArchiveFragment : BaseFragment<FragmentArchiveBinding, ArchiveViewModel>() {
    override val layoutResId = R.layout.fragment_archive
    override val viewModelClass = ArchiveViewModel::class

    private val archiveAdapter by lazy { ArchiveAdapter(viewModel) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initRecyclerView()
        initNavigation()

        viewModel.loadArchiveCapsules()
    }

    private fun initRecyclerView() {
        binding.rvArchive.run {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = archiveAdapter
        }

        viewModel.archiveItems.observe(this) { archiveAdapter.submitList(it) }
    }

    private fun initNavigation() {
        viewModel.navigateToRead.observe(this, EventObserver { navController.navigate(it) })
    }
}
