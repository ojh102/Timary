package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.ui.main.setting.SettingAdapter
import dagger.Binds
import dagger.Module

@Module
abstract class SettingFragmentModule {

    @FragmentScope
    @Binds
    abstract fun bindSettingAdapter(settingAdapter: SettingAdapter): SettingAdapter
}