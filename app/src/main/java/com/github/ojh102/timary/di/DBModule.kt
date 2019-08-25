package com.github.ojh102.timary.di

import android.content.Context
import androidx.room.Room
import com.github.ojh102.timary.data.dao.CapsuleDao
import com.github.ojh102.timary.data.room.TimaryRoomDatabase
import com.github.ojh102.timary.data.datasource.CapsuleDataSource
import com.github.ojh102.timary.data.datasource.CapsuleDataSourceImpl
import com.github.ojh102.timary.data.datasource.SettingDataSource
import com.github.ojh102.timary.data.datasource.SettingDataSourceImpl
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.data.repository.LocalRepositoryImpl
import com.github.ojh102.timary.data.datasource.StoreDateDataSource
import com.github.ojh102.timary.data.datasource.StoreDateDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DBModule.ProvideModule::class])
internal interface DBModule {
    companion object {
        private const val DB_NAME = "timary"
    }

    @Module
    class ProvideModule {
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
    fun storeDateDataSource(storeDateDataSourceImpl: StoreDateDataSourceImpl): StoreDateDataSource

    @Binds
    @Singleton
    fun settignDataSource(settingDataSourceImpl: SettingDataSourceImpl): SettingDataSource
}
