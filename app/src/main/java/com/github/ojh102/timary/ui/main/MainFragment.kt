package com.github.ojh102.timary.ui.main

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentMainBinding

internal class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutRes = R.layout.fragment_main

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }

    private val childNavController: NavController by lazy {
        Navigation.findNavController(view!!.findViewById(R.id.navigation_main_fragment))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initWindow()
        initBottomNav()
    }

    private fun initWindow() {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.setBackgroundDrawableResource(R.drawable.background_gradient_left_to_right)
    }

    private fun initBottomNav() {
        binding.bottomNavigation.run {
            setupWithNavController(childNavController)
            setOnNavigationItemReselectedListener { }
        }
    }
}
