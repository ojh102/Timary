package com.github.ojh102.timary.ui.write.store

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.ViewModelKey
import com.github.ojh102.timary.di.annotation.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [StoreFragmentModule.ProvideModule::class])
internal interface StoreFragmentModule {
    @Module
    class ProvideModule {
        @Provides
        fun provideStoreAdapter(): StoreAdapter {
            return StoreAdapter()
        }
    }

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(StoreViewModel::class)
    fun bindViewModel(viewModel: StoreViewModel): ViewModel
}
