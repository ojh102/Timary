package com.github.ojh102.timary.ui.main.home

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.ViewModelKey
import com.github.ojh102.timary.di.annotation.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [HomeFragmentModule.ProvideModule::class])
internal interface HomeFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(viewModel: HomeViewModel): ViewModel
}
