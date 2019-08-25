package com.github.ojh102.timary.ui.main.setting

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.ViewModelKey
import com.github.ojh102.timary.annotation.FragmentScope
import com.github.ojh102.timary.ui.legacy.main.setting.SettingAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [SettingFragmentModule.ProvideModule::class])
internal interface SettingFragmentModule {
    @Module
    class ProvideModule {
        @Provides
        @FragmentScope
        fun provideSettingAdapter(): SettingAdapter {
            return SettingAdapter()
        }
    }

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    fun bindViewModel(viewModel: SettingViewModel): ViewModel
}
