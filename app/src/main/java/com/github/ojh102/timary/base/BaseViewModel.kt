package com.github.ojh102.timary.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event

internal abstract class BaseViewModel : ViewModel() {
    val toast = MutableLiveData<Event<String>>()
    val navDirections = MutableLiveData<Event<NavDirections>>()
}
