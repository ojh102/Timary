package com.github.ojh102.timary.ui.setting

import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val layoutResId = R.layout.fragment_setting
    override val viewModelClass = SettingViewModel::class

    private val settingAdapter by lazy { SettingAdapter(viewModel) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initRecyclerView()
        initNavigation()

        viewModel.loadSettingItems()
    }

    private fun initRecyclerView() {
        binding.rvSetting.run {
            layoutManager = LinearLayoutManager(context)
            adapter = settingAdapter
        }

        viewModel.settingItems.observe(this) { settingAdapter.submitList(it) }
    }

    private fun initNavigation() {
        viewModel.navigateToTerm.observe(this, EventObserver { navController.navigate(it) })
    }
}
