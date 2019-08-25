package com.github.ojh102.timary.ui.main

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface MainFragmentModule {
    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindViewModel(viewModel: MainViewModel): ViewModel
}