package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.github.ojh102.timary.base.ViewModelFactory
import com.github.ojh102.timary.log.TimaryLogger
import com.github.ojh102.timary.log.TimaryLoggerApi
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.rx.AppSchedulerProvider
import com.github.ojh102.timary.util.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.BindModule::class])
class AppModule {

    @Module
    interface BindModule {
        @Singleton
        @Binds
        fun bindContext(application: Application): Context

        @Binds
        fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Singleton
        @Binds
        fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

        @Singleton
        @Binds
        fun bindTimaryLogger(timaryLogger: TimaryLogger): TimaryLoggerApi
    }

    @Singleton
    @Provides
    fun provideTimaryParser(context: Context): TimaryParser {
        return TimaryParser(context)
    }

    @Singleton
    @Provides
    fun provideResoucre(context: Context): Resources {
        return context.resources
    }

}