package ${packageName}.${packName}

import android.os.Bundle
import androidx.fragment.app.viewModels
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.R
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.base.BaseFragment
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.databinding.Fragment${Name}Binding

internal class ${className}: BaseFragment<Fragment${Name}Binding>() {
    override val layoutRes = R.layout.${fragmentName}

    private val viewModel by viewModels<${Name}ViewModel> { viewModelFactory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel
    }
}
