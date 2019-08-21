package com.github.ojh102.timary.ui.write

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentWriteBinding

internal class WriteFragment : BaseFragment<FragmentWriteBinding>() {
    override val layoutRes = R.layout.fragment_write

    private val viewModel by viewModels<WriteViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
