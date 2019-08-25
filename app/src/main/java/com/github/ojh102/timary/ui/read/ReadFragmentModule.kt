package com.github.ojh102.timary.ui.read

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ReadFragmentModule.ProvideModule::class])
internal interface ReadFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(ReadViewModel::class)
    fun bindViewModel(viewModel: ReadViewModel): ViewModel
}
