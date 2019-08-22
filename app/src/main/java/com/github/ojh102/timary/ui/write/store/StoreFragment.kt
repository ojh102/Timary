package com.github.ojh102.timary.ui.write.store

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentStoreBinding

internal class StoreFragment : BaseFragment<FragmentStoreBinding>() {
    override val layoutRes = R.layout.fragment_store

    private val viewModel by viewModels<StoreViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
