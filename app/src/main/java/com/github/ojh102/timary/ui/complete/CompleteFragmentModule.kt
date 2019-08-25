package com.github.ojh102.timary.ui.complete

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [CompleteFragmentModule.ProvideModule::class])
internal interface CompleteFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(CompleteViewModel::class)
    fun bindViewModel(viewModel: CompleteViewModel): ViewModel
}
