package com.github.ojh102.timary.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.github.ojh102.timary.db.TimaryDB
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import com.github.ojh102.timary.db.room.CapsuleDao
import com.github.ojh102.timary.db.room.TimaryRoomDatabase
import com.github.ojh102.timary.repository.CapsuleDataSource
import com.github.ojh102.timary.repository.CapsuleDataSourceImpl
import com.github.ojh102.timary.repository.LocalRepository
import com.github.ojh102.timary.repository.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DBModule.ProvideModule::class])
internal interface DBModule {
    companion object {
        private const val DB_NAME = "timary"
        private const val PREF_NAME = "$DB_NAME.pref"
    }

    @Module
    class ProvideModule {
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

        @Provides
        @Singleton
        fun provideRoomDatabase(context: Context): TimaryRoomDatabase {
            return Room.databaseBuilder(context, TimaryRoomDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @Singleton
        fun provideCapsuleDao(roomDatabase: TimaryRoomDatabase): CapsuleDao {
            return roomDatabase.capsuleDao()
        }
    }

    @Binds
    @Singleton
    fun capsuleDataSource(capsuleDataSourceImpl: CapsuleDataSourceImpl): CapsuleDataSource

    @Binds
    @Singleton
    fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}
