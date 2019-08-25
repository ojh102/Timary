package com.github.ojh102.timary.ui.main.home

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.ViewModelKey
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.util.TimaryParser
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [HomeFragmentModule.ProvideModule::class])
internal interface HomeFragmentModule {
    @Module
    class ProvideModule {
        @Provides
        @FragmentScope
        fun provideHomeAdapter(timaryParser: TimaryParser): HomeAdapter {
            return HomeAdapter(timaryParser)
        }
    }

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindViewModel(viewModel: HomeViewModel): ViewModel
}
