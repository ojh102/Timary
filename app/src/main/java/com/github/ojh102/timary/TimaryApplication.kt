package com.github.ojh102.timary

import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.github.ojh102.timary.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import timber.log.Timber

class TimaryApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

        Realm.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}
