package com.github.ojh102.timary.ui.main.archive

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentArchiveBinding

internal class ArchiveFragment : BaseFragment<FragmentArchiveBinding>() {
    override val layoutRes = R.layout.fragment_archive

    private val viewModel by viewModels<ArchiveViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
