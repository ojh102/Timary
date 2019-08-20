package com.github.ojh102.timary.ui.main.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentHomeBinding

internal class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutRes = R.layout.fragment_home

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
