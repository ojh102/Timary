package com.github.ojh102.timary.di

import android.content.Context
import androidx.room.Room
import com.github.ojh102.timary.data.dao.CapsuleDao
import com.github.ojh102.timary.data.room.TimaryRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DBModule {
    companion object {
        private const val DB_NAME = "timary"
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): TimaryRoomDatabase {
        return Room.databaseBuilder(context, TimaryRoomDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCapsuleDao(roomDatabase: TimaryRoomDatabase): CapsuleDao {
        return roomDatabase.capsuleDao()
    }
}
