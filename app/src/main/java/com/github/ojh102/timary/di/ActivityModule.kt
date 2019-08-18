package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.ui.complete.CompleteActivity
import com.github.ojh102.timary.ui.main.MainActivity
import com.github.ojh102.timary.ui.read.ReadActivity
import com.github.ojh102.timary.ui.splash.SplashActivity
import com.github.ojh102.timary.ui.write.content.WriteActivity
import com.github.ojh102.timary.ui.write.store.StoreActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeWriteActivity(): WriteActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [StoreActivityModule::class])
    abstract fun contributeStoreActivity(): StoreActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeReadActivity(): ReadActivity

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeCompleteActivity(): CompleteActivity
}
