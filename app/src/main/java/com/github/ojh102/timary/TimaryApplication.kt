package com.github.ojh102.timary

import com.amplitude.api.Amplitude
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.github.ojh102.timary.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
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

        FirebaseApp.initializeApp(this)

        Fabric.with(this, Crashlytics())

        Amplitude.getInstance().initialize(this, BuildConfig.TIMARY_AMPLITUDE_KEY).enableForegroundTracking(this)

        Realm.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}