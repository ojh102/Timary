package com.github.ojh102.timary.di

import com.github.ojh102.timary.annotation.ActivityScope
import com.github.ojh102.timary.ui.write.store.StoreAdapter
import dagger.Module
import dagger.Provides

@Module
class StoreActivityModule {

    @ActivityScope
    @Provides
    fun provideStoreAdapter(): StoreAdapter {
        return StoreAdapter()
    }
}