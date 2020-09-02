package com.github.ojh102.timary

import android.app.Application
import com.facebook.stetho.Stetho
import com.github.ojh102.timary.util.ResourcesUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
internal class TimaryApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ResourcesUtil.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }
}
