package com.github.ojh102.timary.di

import com.github.ojh102.timary.fcm.TimaryFirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeTimaryFirebaseMessageService(): TimaryFirebaseMessagingService
    
}