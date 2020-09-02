package com.github.ojh102.timary.ui.setting

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.di.annotation.FragmentScope
import com.github.ojh102.timary.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SettingFragmentModule.ProvideModule::class])
internal interface SettingFragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    fun bindViewModel(viewModel: SettingViewModel): ViewModel
}
