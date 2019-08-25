package com.github.ojh102.timary.ui.main.archive

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.ViewModelKey
import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.util.TimaryParser
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ArchiveFragmentModule.ProvideModule::class])
internal interface ArchiveFragmentModule {
    @Module
    class ProvideModule {
        @Provides
        @FragmentScope
        fun provideArchiveAdapter(timaryParser: TimaryParser): ArchiveAdapter {
            return ArchiveAdapter(timaryParser)
        }
    }

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(ArchiveViewModel::class)
    fun bindViewModel(viewModel: ArchiveViewModel): ViewModel
}
