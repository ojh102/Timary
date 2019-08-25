package com.github.ojh102.timary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.ojh102.timary.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

internal abstract class BaseFragment<VB : ViewDataBinding> : DaggerFragment() {
    protected abstract val layoutRes: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: VB

    protected open val navController: NavController
        get() = activity!!.findNavController(R.id.nav_host_fragment)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
