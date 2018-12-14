package com.github.ojh102.timary.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import com.github.ojh102.timary.base.ViewModelFactory
import com.github.ojh102.timary.util.TimaryParser
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
    }

    @Singleton
    @Provides
    fun provideTimaryParser(context: Context): TimaryParser {
        return TimaryParser(context)
    }

}