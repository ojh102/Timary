package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.github.ojh102.timary.db.TimaryDB
import com.github.ojh102.timary.db.TimaryRealmMigration
import com.github.ojh102.timary.db.TimaryRealmScheme
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import com.github.ojh102.timary.util.TimaryParser
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class DBModule {

    companion object {
        private const val TAG = "timary"
        private const val PREF_NAME = "$TAG.pref"
        private const val REALM_NAME = "$TAG.realm"
        private const val REALM_SCHEME_VERSION = 1L
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideTimarySharePreferencesManager(sharedPreferences: SharedPreferences): TimarySharedPreferenceManager {
        return TimarySharedPreferenceManager(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
                .schemaVersion(REALM_SCHEME_VERSION)
                .name(REALM_NAME)
                .modules(TimaryRealmScheme())
                .migration(TimaryRealmMigration())
                .compactOnLaunch()
                .build()
    }

    @Singleton
    @Provides
    fun provideTimaryDB(realmConfiguration: RealmConfiguration): TimaryDB {
        return TimaryDB(realmConfiguration)
    }

    @Singleton
    @Provides
    fun provideTimaryParser(context: Context): TimaryParser {
        return TimaryParser(context)
    }

}