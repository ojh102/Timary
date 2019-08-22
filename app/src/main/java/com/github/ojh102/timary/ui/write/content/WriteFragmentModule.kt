package com.github.ojh102.timary.ui.write.content

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.ViewModelKey
import com.github.ojh102.timary.annotation.FragmentScope
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
