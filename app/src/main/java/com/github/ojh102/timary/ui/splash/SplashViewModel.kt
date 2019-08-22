package com.github.ojh102.timary.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SplashViewModel @Inject constructor() : BaseViewModel() {
    private val _navigateToMain = MutableLiveData<Event<NavDirections>>()
    val navigateToMain: LiveData<Event<NavDirections>> = _navigateToMain

    fun initSplash() {
        viewModelScope.launch {
            delay(2000)

            _navigateToMain.value = Event(SplashFragmentDirections.actionSplashFragmentToMainFragment())
        }
    }

}