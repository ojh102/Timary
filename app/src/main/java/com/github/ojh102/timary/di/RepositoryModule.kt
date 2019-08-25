package com.github.ojh102.timary.di

import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.data.repository.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Binds
    @Singleton
    fun localRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}
