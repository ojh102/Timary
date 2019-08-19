package ${packageName}.${packName}

import androidx.lifecycle.ViewModel
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.annotation.ViewModelKey
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.annotation.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [${Name}ActivityModule.ProvideModule::class])
internal interface ${Name}ActivityModule {
    @Module
    class ProvideModule

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(${Name}ActivityViewModel::class)
    fun bindViewModel(viewModel: ${Name}ActivityViewModel): ViewModel
}
