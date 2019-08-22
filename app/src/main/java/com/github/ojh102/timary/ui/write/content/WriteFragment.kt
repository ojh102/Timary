package com.github.ojh102.timary.ui.write.content

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.EventObserver
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentWriteBinding

internal class WriteFragment : BaseFragment<FragmentWriteBinding>() {
    override val layoutRes = R.layout.fragment_write

    private val viewModel by viewModels<WriteViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()
        initNavigation()
    }

    private fun initToolbar() {
        binding.toolbar.run {
            inflateMenu(R.menu.menu_write)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_write -> {
                        viewModel.clickWrite()
                    }
                }

                false
            }
        }
    }

    private fun initNavigation() {
        viewModel.navigateToStore.observe(this, EventObserver {
            navController.navigate(it)
        })
    }
}
