package com.github.ojh102.timary.ui.text

import android.os.Bundle
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseFragment
import com.github.ojh102.timary.databinding.FragmentTextBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class TextFragment : BaseFragment<FragmentTextBinding, TextViewModel>() {
    override val layoutResId = R.layout.fragment_text
    override val viewModelClass = TextViewModel::class

    private val args by navArgs<TextFragmentArgs>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        initToolbar()
        initView()

        viewModel.arguments(args)
    }

    private fun initToolbar() {
        binding.toolbar.run {
            setNavigationOnClickListener {
                navController.popBackStack()
            }
        }

        viewModel.title.observe(this) { binding.toolbar.title = it }
    }

    private fun initView() {
        viewModel.content.observe(this) { binding.tvContent.text = it }
    }
}
