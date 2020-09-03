package com.github.ojh102.timary.ui.write

import android.os.Bundle
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentWriteBinding
import com.github.ojh102.timary.util.extension.hideKeyboard
import com.github.ojh102.timary.util.extension.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class WriteFragment : BaseFragment<FragmentWriteBinding, WriteViewModel>() {
    override val layoutResId = R.layout.fragment_write
    override val viewModelClass = WriteViewModel::class

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()
        initNavigation()
        initView()
    }

    private fun initToolbar() {
        binding.toolbar.run {
            inflateMenu(R.menu.menu_write)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_write -> {
                        viewModel.onClickWrite()
                    }
                }

                false
            }

            setNavigationOnClickListener { navController.popBackStack() }
        }
    }

    private fun initNavigation() {
        viewModel.navigateToStore.observe(
            this,
            EventObserver {
                binding.tvWrite.hideKeyboard(0L)
                navController.navigate(it)
            }
        )
    }

    private fun initView() {
        binding.tvWrite.showKeyboard(0L)
    }
}
