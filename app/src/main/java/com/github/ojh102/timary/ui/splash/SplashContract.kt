package com.github.ojh102.timary.ui.splash

import com.github.ojh102.timary.base.LegacyBaseViewModel
import javax.inject.Inject

interface SplashContract {

    interface Inputs

    interface Outputs

    class SplashViewModel @Inject constructor() : LegacyBaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this
    }
}
