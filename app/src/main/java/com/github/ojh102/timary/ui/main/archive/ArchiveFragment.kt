package com.github.ojh102.timary.ui.main.archive

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentArchiveBinding
import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.ui.legacy.main.archieve.ArchiveAdapter
import javax.inject.Inject

internal class ArchiveFragment : BaseFragment<FragmentArchiveBinding>() {
    override val layoutRes = R.layout.fragment_archive

    override val navController: NavController
        get() = activity!!.findNavController(R.id.nav_host_fragment)

    private val viewModel by viewModels<ArchiveViewModel> { viewModelFactory }

    @Inject
    lateinit var archiveAdapter: ArchiveAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initRecyclerView()

        viewModel.navigateToRead.observe(this, EventObserver { navController.navigate(it) })

        viewModel.loadArchiveCapsules()
    }

    private fun initRecyclerView() {
        binding.rvArchive.run {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = archiveAdapter
        }

        archiveAdapter.setCallbacks(object : ArchiveAdapter.Callbacks {
            override fun onClickArchiveCapsule(capsule: Capsule) {
                viewModel.onClickArchiveCapsule(capsule)
            }
        })

        viewModel.capsules.observe(this) { archiveAdapter.submitList(it) }
    }
}
