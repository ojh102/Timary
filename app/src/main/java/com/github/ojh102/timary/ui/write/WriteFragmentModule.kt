package com.github.ojh102.timary.ui.write

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [WriteFragmentModule.ProvideModule::class])
internal interface WriteFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(WriteViewModel::class)
    fun bindViewModel(viewModel: WriteViewModel): ViewModel
}
