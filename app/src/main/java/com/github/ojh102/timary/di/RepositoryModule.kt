package com.github.ojh102.timary.di

import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.data.repository.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}
