package com.github.ojh102.timary.ui

import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface TimaryActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(TimaryActivityViewModel::class)
    fun bindViewModel(timaryActivityViewModel: TimaryActivityViewModel)
}