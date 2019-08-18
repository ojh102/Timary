package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.ojh102.timary.base.ViewModelFactory
import com.github.ojh102.timary.util.rx.AppSchedulerProvider
import com.github.ojh102.timary.util.rx.SchedulerProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface AppModule {
    @Singleton
    @Binds
    fun bindContext(application: Application): Context

    @Singleton
    @Binds
    fun bindSchedulerProvider(schedulerProvider: AppSchedulerProvider): SchedulerProvider

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
