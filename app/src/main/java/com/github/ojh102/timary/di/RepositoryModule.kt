package com.github.ojh102.timary.di

import android.content.Context
import com.github.ojh102.timary.db.TimaryDB
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import com.github.ojh102.timary.repository.CapsuleRepository
import com.github.ojh102.timary.repository.SettingRepository
import com.github.ojh102.timary.repository.StoreDateRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCapsuleRepository(timaryDB: TimaryDB): CapsuleRepository {
        return CapsuleRepository(timaryDB)
    }

    @Singleton
    @Provides
    fun provideSettingRepository(context: Context, timarySharedPreferenceManager: TimarySharedPreferenceManager): SettingRepository {
        return SettingRepository(context, timarySharedPreferenceManager)
    }

    @Singleton
    @Provides
    fun provideStoreDateRepository(context: Context): StoreDateRepository {
        return StoreDateRepository(context)
    }

}
