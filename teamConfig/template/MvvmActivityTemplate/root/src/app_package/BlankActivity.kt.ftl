package ${packageName}.${packName}

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.R
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.base.BaseActivity
import ${applicationPackage?replace('.debug|.qa|.release', '', 'r')}.databinding.Activity${Name}Binding

internal class ${className}: BaseActivity<Activity${Name}Binding>() {
    override val layoutRes = R.layout.${activityName}

    private val viewModel by viewModles<${Name}ActivityViewModel> { viewModelFactory }

    override val viewModelClass = ${Name}ActivityViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
    }
}
