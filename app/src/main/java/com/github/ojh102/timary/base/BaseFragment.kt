package com.github.ojh102.timary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

internal abstract class BaseFragment<VB : ViewDataBinding> : DaggerFragment() {
    protected abstract val layoutRes: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: VB

    protected val navController: NavController
        get() = NavHostFragment.findNavController(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        return binding.root
    }
}