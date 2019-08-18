package com.github.ojh102.timary.di

import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.annotation.ViewModelKey
import com.github.ojh102.timary.ui.complete.CompleteContract
import com.github.ojh102.timary.ui.main.MainContract
import com.github.ojh102.timary.ui.main.archieve.ArchiveContract
import com.github.ojh102.timary.ui.main.home.HomeContract
import com.github.ojh102.timary.ui.main.setting.SettingContract
import com.github.ojh102.timary.ui.read.ReadContract
import com.github.ojh102.timary.ui.splash.SplashContract
import com.github.ojh102.timary.ui.write.content.WriteContract
import com.github.ojh102.timary.ui.write.store.StoreContract
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashContract.SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashContract.SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainContract.MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainContract.MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeContract.HomeViewModel::class)
    abstract fun bindHomeViewMdoel(homeViewModel: HomeContract.HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArchiveContract.ArchiveViewModel::class)
    abstract fun bindArchiveViewMdoel(archiveContract: ArchiveContract.ArchiveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingContract.SettingViewModel::class)
    abstract fun bindSettingViewModel(settingViewModel: SettingContract.SettingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WriteContract.WriteViewModel::class)
    abstract fun bindWriteViewModel(writeViewModel: WriteContract.WriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StoreContract.StoreViewModel::class)
    abstract fun bindStoreViewModel(storeViewModel: StoreContract.StoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReadContract.ReadViewModel::class)
    abstract fun bindReadViewModel(readViewModel: ReadContract.ReadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompleteContract.CompleteViewModel::class)
    abstract fun bindCompleteViewModel(completeViewModel: CompleteContract.CompleteViewModel): ViewModel
}
