package com.github.ojh102.timary.ui.main.setting

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentSettingBinding
import com.github.ojh102.timary.ui.legacy.main.setting.SettingAdapter
import javax.inject.Inject

internal class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override val layoutRes = R.layout.fragment_setting

    override val navController: NavController
        get() = activity!!.findNavController(R.id.nav_host_fragment)

    private val viewModel by viewModels<SettingViewModel> { viewModelFactory }

    @Inject
    lateinit var settingAdapter: SettingAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initRecyclerView()

        viewModel.navigateToTerm.observe(this, EventObserver { navController.navigate(it) })

        viewModel.loadSettingItems()
    }

    private fun initRecyclerView() {
        binding.rvSetting.run {
            layoutManager = LinearLayoutManager(context)
            adapter = settingAdapter
        }

        settingAdapter.setCallbacks(object : SettingAdapter.Callbacks {
            override fun onClickTerm() {
                viewModel.onClickTerm()
            }
        })

        viewModel.settingItems.observe(this) { settingAdapter.submitList(it) }
    }
}
