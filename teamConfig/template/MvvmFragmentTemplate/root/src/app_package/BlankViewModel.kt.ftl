package ${packageName}.${packName}

import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.base.BaseViewModel
import javax.inject.Inject

internal class ${Name}ViewModel @Inject constructor() : BaseViewModel()
