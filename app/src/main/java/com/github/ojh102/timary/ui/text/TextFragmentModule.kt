package com.github.ojh102.timary.ui.text

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [TextFragmentModule.ProvideModule::class])
internal interface TextFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(TextViewModel::class)
    fun bindViewModel(viewModel: TextViewModel): ViewModel
}
