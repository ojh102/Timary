package com.github.ojh102.timary.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SplashViewModel @Inject constructor() : BaseViewModel() {
    private val _navigateToMain = MutableLiveData<Event<Unit>>()
    val navigateToMain: LiveData<Event<Unit>> = _navigateToMain

    fun initSplash() {
        viewModelScope.launch {
            delay(2000)

            _navigateToMain.value = Event(Unit)
        }
    }

}