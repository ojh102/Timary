package com.github.ojh102.timary.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event

internal abstract class BaseViewModel : ViewModel() {
    protected val _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>> = _toast

    protected val _navDirection = MutableLiveData<Event<NavDirections>>()
    val navDirections: LiveData<Event<NavDirections>> = _navDirection
}
