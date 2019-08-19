package com.github.ojh102.timary.ui.splash

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentSplashBinding

internal class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val layoutRes = R.layout.fragment_splash

    private val viewModel by viewModels<SplashViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        setupNavigation()

        viewModel.initSplash()
    }

    private fun setupNavigation() {
        viewModel.navigateToMain.observe(this, EventObserver {
            navController.navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment().actionId)
        })
    }
}
