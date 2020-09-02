package com.github.ojh102.timary

import com.facebook.stetho.Stetho
import com.github.ojh102.timary.di.DaggerAppComponent
import com.github.ojh102.timary.util.ResourcesUtil
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

internal class TimaryApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        ResourcesUtil.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}
