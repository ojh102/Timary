package com.github.ojh102.timary.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SplashViewModel @ViewModelInject constructor() : BaseViewModel() {
    fun initSplash() {
        viewModelScope.launch {
            delay(2000)

            navDirections.value = Event(SplashFragmentDirections.actionSplashFragmentToMainFragment())
        }
    }
}
