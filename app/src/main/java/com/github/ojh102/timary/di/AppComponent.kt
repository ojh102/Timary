package com.github.ojh102.timary.di

import android.app.Application
import com.github.ojh102.timary.TimaryApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        DBModule::class,
        RepositoryModule::class,
        AppModule::class
    ]
)
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: TimaryApplication)
}
