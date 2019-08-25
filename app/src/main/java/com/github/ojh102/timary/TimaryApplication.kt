package com.github.ojh102.timary

import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.github.ojh102.timary.di.DaggerAppComponent
import com.github.ojh102.timary.util.ResourcesUtil
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric
import timber.log.Timber

internal class TimaryApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        AndroidThreeTen.init(this)
        ResourcesUtil.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}
