package com.github.ojh102.timary.ui.main

import com.github.ojh102.timary.base.BaseViewModel
import javax.inject.Inject

interface MainContract {

    interface Inputs

    interface Outputs

    class MainViewModel @Inject constructor(): BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

    }

}
