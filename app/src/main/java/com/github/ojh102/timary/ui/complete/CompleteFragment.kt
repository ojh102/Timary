package com.github.ojh102.timary.ui.complete

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentCompleteBinding

internal class CompleteFragment : BaseFragment<FragmentCompleteBinding>() {
    override val layoutRes = R.layout.fragment_complete

    private val viewModel by viewModels<CompleteViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
