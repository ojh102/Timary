package com.github.ojh102.timary.ui.main.setting

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentSettingBinding

internal class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override val layoutRes = R.layout.fragment_setting

    private val viewModel by viewModels<SettingViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
