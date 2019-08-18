package com.github.ojh102.timary.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityTimaryBinding

internal class TimaryActivity : BaseActivity<ActivityTimaryBinding>() {
    override val layoutRes = R.layout.activity_timary

    private val viewModel by viewModels<TimaryActivityViewModel> { viewModelFactory }

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}