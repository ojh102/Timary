package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.ojh102.timary.base.ViewModelFactory
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.rx.AppSchedulerProvider
import com.github.ojh102.timary.util.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
internal interface AppModule {
    @Module
    class ProvideModule {
        @Provides
        @Singleton
        fun provideTimaryParser(context: Context): TimaryParser {
            return TimaryParser(context)
        }
    }

    @Binds
    @Singleton
    fun bindContext(application: Application): Context

    @Binds
    @Singleton
    fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
