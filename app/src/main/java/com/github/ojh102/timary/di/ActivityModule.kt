package com.github.ojh102.timary.di

import android.content.Context
import com.github.ojh102.timary.di.annotation.ActivityScope
import com.github.ojh102.timary.ui.TimaryActivity
import com.github.ojh102.timary.ui.TimaryActivityModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
internal interface ActivityModule {
    @Module
    class ProvideModule

    @ActivityScope
    @ContributesAndroidInjector(modules = [TimaryActivityModule::class])
    fun bindTimaryActivity(): TimaryActivity

}
