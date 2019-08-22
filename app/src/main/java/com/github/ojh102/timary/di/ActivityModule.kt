package com.github.ojh102.timary.di

import android.content.Context
import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.ui.TimaryActivity
import com.github.ojh102.timary.ui.TimaryActivityModule
import com.github.ojh102.timary.util.TimaryParser
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
internal interface ActivityModule {
    @Module
    class ProvideModule {
        @Provides
        @Singleton
        fun provideTimaryParser(context: Context): TimaryParser {
            return TimaryParser(context)
        }
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [TimaryActivityModule::class])
    fun bindTimaryActivity(): TimaryActivity

}
