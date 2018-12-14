package com.github.ojh102.timary.ui.main.setting

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentSettingBinding
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingContract.SettingViewModel>() {

    companion object {
        const val TAG = "setting"

        fun newInstance() = SettingFragment()
    }

    private val settingAdapter = SettingAdapter()

    override fun getLayoutRes() = R.layout.fragment_setting
    override fun getModelClass() = SettingContract.SettingViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.inputs = viewModel

        initializeRecyclerView()

        bindObservable()
    }

    private fun initializeRecyclerView() {
        rv_setting.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = settingAdapter
        }
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.settingItemList()
                        .subscribe(settingAdapter::submitList)
        )
    }
}