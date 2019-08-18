package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.ojh102.timary.db.TimaryDB
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class DBModule {
    companion object {
        private const val TAG = "timary"
        private const val PREF_NAME = "$TAG.pref"
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTimarySharePreferencesManager(sharedPreferences: SharedPreferences): TimarySharedPreferenceManager {
        return TimarySharedPreferenceManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideTimaryDB(): TimaryDB {
        return TimaryDB()
    }
}
