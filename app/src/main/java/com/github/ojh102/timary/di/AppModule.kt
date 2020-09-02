package com.github.ojh102.timary.di

import com.github.ojh102.timary.util.ResourcesProvider
import com.github.ojh102.timary.util.ResourcesProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {
    @Binds
    @Singleton
    fun bindResourcesProvider(resourcesProvider: ResourcesProviderImpl): ResourcesProvider
}
