package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.ui.TimaryActivity
import com.github.ojh102.timary.ui.TimaryActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [TimaryActivityModule::class])
    fun bindTimaryActivity(): TimaryActivity
}
