package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.ui.main.archieve.ArchiveFragment
import com.github.ojh102.timary.ui.main.home.HomeFragment
import com.github.ojh102.timary.ui.main.setting.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ArchiveFragmentModule::class])
    abstract fun contributeArchiveFragment(): ArchiveFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SettingFragmentModule::class])
    abstract fun contributeSettingFragment(): SettingFragment

}