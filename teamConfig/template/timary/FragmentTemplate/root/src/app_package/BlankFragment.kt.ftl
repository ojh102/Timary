package ${packageName}.${packName}

import android.os.Bundle
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.R
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.base.fragment.BaseFragment
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.databinding.Fragment${Name}Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ${className}: BaseFragment<Fragment${Name}Binding, ${Name}ViewModel>() {

    override val layoutResIdId = R.layout.${fragmentName}
    override val viewModelClass = ${Name}ViewModel::class

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
