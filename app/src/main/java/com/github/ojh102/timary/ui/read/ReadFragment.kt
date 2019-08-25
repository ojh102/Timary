package com.github.ojh102.timary.ui.read

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentReadBinding

internal class ReadFragment : BaseFragment<FragmentReadBinding>() {
    override val layoutRes = R.layout.fragment_read

    private val viewModel by viewModels<ReadViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
