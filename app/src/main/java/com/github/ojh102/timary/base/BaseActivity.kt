package com.github.ojh102.timary.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

internal abstract class BaseActivity<VB: ViewDataBinding> : DaggerAppCompatActivity() {
    protected abstract val layoutRes: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }
}