package com.github.ojh102.timary.ui.main.archive

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.ViewModelKey
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.util.TimaryParser
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ArchiveFragmentModule.ProvideModule::class])
internal interface ArchiveFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(ArchiveViewModel::class)
    fun bindViewModel(viewModel: ArchiveViewModel): ViewModel
}
