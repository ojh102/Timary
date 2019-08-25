package ${packageName}.${packName}

import androidx.lifecycle.ViewModel
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.annotation.ViewModelKey
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.annotation.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [${Name}FragmentModule.ProvideModule::class])
internal interface ${Name}FragmentModule {
    @Module
    class ProvideModule

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(${Name}ViewModel::class)
    fun bindViewModel(viewModel: ${Name}ViewModel): ViewModel
}
