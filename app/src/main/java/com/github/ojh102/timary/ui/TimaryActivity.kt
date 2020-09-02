package com.github.ojh102.timary.ui

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseActivity
import com.github.ojh102.timary.databinding.ActivityTimaryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
internal class TimaryActivity : BaseActivity<ActivityTimaryBinding, TimaryActivityViewModel>() {
    override val layoutResId = R.layout.activity_timary
    override val viewModelClass = TimaryActivityViewModel::class

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
