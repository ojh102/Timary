package ${packageName}.${packName}

import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.base.BaseViewModel
import javax.inject.Inject

internal class ${Name}ActivityViewModel @Inject constructor(private val useCase: ${Name}ActivityUseCase) : BaseViewModel()
