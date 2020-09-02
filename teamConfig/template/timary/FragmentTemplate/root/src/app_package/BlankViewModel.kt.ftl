package ${packageName}.${packName}

import androidx.hilt.lifecycle.ViewModelInject
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.base.viewmodel.BaseViewModel

internal class ${Name}ViewModel @ViewModelInject constructor() : BaseViewModel()
