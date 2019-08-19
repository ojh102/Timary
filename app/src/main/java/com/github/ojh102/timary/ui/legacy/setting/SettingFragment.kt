package com.github.ojh102.timary.ui.legacy.setting

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.LegacyBaseFragment
import com.github.ojh102.timary.databinding.FragmentSettingBinding
import com.github.ojh102.timary.util.Navigator
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_setting.*

internal class SettingFragment : LegacyBaseFragment<FragmentSettingBinding, SettingContract.SettingViewModel>() {

    companion object {
        const val TAG = "setting"

        fun newInstance() = SettingFragment()
    }

    @Inject
    lateinit var settingAdapter: SettingAdapter

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
            layoutManager = LinearLayoutManager(context)
            adapter = settingAdapter
        }

        settingAdapter.setCallbacks(object : SettingAdapter.Callbacks {
            override fun onCheckedAlert(checked: Boolean) {
                viewModel.inputs.onCheckedAlert(checked)
            }

            override fun onClickTerm() {
                viewModel.inputs.onClickTerm()
            }
        })
    }

    private fun bindObservable() {
        bind(
                viewModel.outputs.settingItemList()
                        .observeOn(schedulerProvider.ui())
                        .subscribe(settingAdapter::submitList),

                viewModel.outputs.navigateToTerm()
                        .observeOn(schedulerProvider.ui())
                        .subscribeBy(
                                onNext = {
                                    context?.let { context ->
                                        Navigator.navigateToTermTextActivity(context)
                                    }
                                }
                        )
        )
    }
}
