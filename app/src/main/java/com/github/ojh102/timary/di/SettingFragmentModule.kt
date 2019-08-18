package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.ui.main.setting.SettingAdapter
import dagger.Module
import dagger.Provides

@Module
class SettingFragmentModule {

    @FragmentScope
    @Provides
    fun provideSettingAdapter(): SettingAdapter {
        return SettingAdapter()
    }
}
