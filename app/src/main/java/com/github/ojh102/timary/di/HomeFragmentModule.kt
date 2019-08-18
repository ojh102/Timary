package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.ui.main.home.HomeAdapter
import com.github.ojh102.timary.util.TimaryParser
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @FragmentScope
    @Provides
    fun provideHomeAdapter(timaryParser: TimaryParser): HomeAdapter {
        return HomeAdapter(timaryParser)
    }
}
