package com.github.ojh102.timary.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactoty: ViewModelProvider.Factory
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    protected abstract fun getModelClass(): Class<VM>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactoty).get(getModelClass())
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    protected fun bind(vararg disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

}