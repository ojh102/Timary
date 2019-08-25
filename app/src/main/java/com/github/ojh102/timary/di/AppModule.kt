package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.ojh102.timary.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
internal interface AppModule {
    @Module
    class ProvideModule

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    fun bindContext(application: Application): Context
}
