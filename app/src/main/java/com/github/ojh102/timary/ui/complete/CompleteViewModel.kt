package com.github.ojh102.timary.ui.complete

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class CompleteViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _popup = MutableLiveData<Event<Unit>>()
    val popup: LiveData<Event<Unit>> = _popup

    init {
        viewModelScope.launch {
            delay(3000)
            _popup.value = Event(Unit)
        }
    }

    fun argument(argument: CompleteFragmentArgs) {
        _title.value = argument.title
        _description.value = argument.description
    }
}
