package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.ui.TimaryActivity
import com.github.ojh102.timary.ui.TimaryActivityModule
import com.github.ojh102.timary.ui.complete.CompleteActivity
import com.github.ojh102.timary.ui.main.MainActivity
import com.github.ojh102.timary.ui.read.ReadActivity
import com.github.ojh102.timary.ui.splash.SplashActivity
import com.github.ojh102.timary.ui.write.content.WriteActivity
import com.github.ojh102.timary.ui.write.store.StoreActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [TimaryActivityModule::class])
    fun bindTimaryActivity(): TimaryActivity

    @ActivityScope
    @ContributesAndroidInjector()
    fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector()
    fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector()
    fun contributeWriteActivity(): WriteActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [StoreActivityModule::class])
    fun contributeStoreActivity(): StoreActivity

    @ActivityScope
    @ContributesAndroidInjector()
    fun contributeReadActivity(): ReadActivity

    @ActivityScope
    @ContributesAndroidInjector()
    fun contributeCompleteActivity(): CompleteActivity
}
