package com.github.ojh102.timary.ui.main.archive

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentArchiveBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.ui.legacy.main.archieve.ArchiveAdapter
import javax.inject.Inject

internal class ArchiveFragment : BaseFragment<FragmentArchiveBinding>() {
    override val layoutRes = R.layout.fragment_archive

    private val viewModel by viewModels<ArchiveViewModel> { viewModelFactory }

    @Inject
    lateinit var archiveAdapter: ArchiveAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvArchive.run {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = archiveAdapter
        }

        archiveAdapter.setCallbacks(object : ArchiveAdapter.Callbacks {
            override fun onClickArchiveCapsule(capsule: com.github.ojh102.timary.model.Capsule) {

            }
        })
    }
}
