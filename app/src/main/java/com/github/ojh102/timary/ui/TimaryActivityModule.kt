package com.github.ojh102.timary.ui

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.annotation.ViewModelKey
import com.github.ojh102.timary.ui.complete.CompleteFragment
import com.github.ojh102.timary.ui.complete.CompleteFragmentModule
import com.github.ojh102.timary.ui.main.MainFragment
import com.github.ojh102.timary.ui.main.MainFragmentModule
import com.github.ojh102.timary.ui.main.archive.ArchiveFragment
import com.github.ojh102.timary.ui.main.archive.ArchiveFragmentModule
import com.github.ojh102.timary.ui.main.home.HomeFragment
import com.github.ojh102.timary.ui.main.home.HomeFragmentModule
import com.github.ojh102.timary.ui.main.setting.SettingFragment
import com.github.ojh102.timary.ui.main.setting.SettingFragmentModule
import com.github.ojh102.timary.ui.splash.SplashFragment
import com.github.ojh102.timary.ui.splash.SplashFragmentModule
import com.github.ojh102.timary.ui.write.content.WriteFragment
import com.github.ojh102.timary.ui.write.content.WriteFragmentModule
import com.github.ojh102.timary.ui.write.store.StoreFragment
import com.github.ojh102.timary.ui.write.store.StoreFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal interface TimaryActivityModule {
    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(TimaryActivityViewModel::class)
    fun bindViewModel(timaryActivityViewModel: TimaryActivityViewModel): ViewModel

    @ContributesAndroidInjector(modules = [SplashFragmentModule::class])
    @FragmentScope
    fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    @FragmentScope
    fun bindMainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    @FragmentScope
    fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [ArchiveFragmentModule::class])
    @FragmentScope
    fun bindArchiveFragment(): ArchiveFragment

    @ContributesAndroidInjector(modules = [SettingFragmentModule::class])
    @FragmentScope
    fun bindSettingFragment(): SettingFragment

    @ContributesAndroidInjector(modules = [WriteFragmentModule::class])
    @FragmentScope
    fun bindWriteFragment(): WriteFragment

    @ContributesAndroidInjector(modules = [StoreFragmentModule::class])
    @FragmentScope
    fun bindStoreFragment(): StoreFragment

    @ContributesAndroidInjector(modules = [CompleteFragmentModule::class])
    @FragmentScope
    fun bindCompleteFragment(): CompleteFragment
}