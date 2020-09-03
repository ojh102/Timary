package com.github.ojh102.timary.ui.splash

import android.os.Bundle
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override val layoutResId = R.layout.fragment_splash
    override val viewModelClass = SplashViewModel::class

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initNavigation()

        viewModel.initSplash()
    }

    private fun initNavigation() {
        viewModel.navDirections.observe(
            this,
            EventObserver {
                navController.navigate(it)
            }
        )
    }
}
