package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.ui.main.archieve.ArchiveAdapter
import com.github.ojh102.timary.util.TimaryParser
import dagger.Module
import dagger.Provides

@Module
class ArchiveFragmentModule {

    @FragmentScope
    @Provides
    fun provideArchiveAdapter(timaryParser: TimaryParser): ArchiveAdapter {
        return ArchiveAdapter(timaryParser)
    }
}
