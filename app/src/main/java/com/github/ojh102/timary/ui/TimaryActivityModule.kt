package com.github.ojh102.timary.ui

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.annotation.ViewModelKey
import com.github.ojh102.timary.ui.splash.SplashFragment
import com.github.ojh102.timary.ui.splash.SplashFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal interface TimaryActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(TimaryActivityViewModel::class)
    fun bindViewModel(timaryActivityViewModel: TimaryActivityViewModel): ViewModel

    @ContributesAndroidInjector(modules = [SplashFragmentModule::class])
    @FragmentScope
    fun bindSplashFragment(): SplashFragment
}