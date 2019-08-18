package com.github.ojh102.timary.ui.splash

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SplashFragmentModule {
    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindViewModel(viewModel: SplashViewModel): ViewModel
}