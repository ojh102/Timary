package com.github.ojh102.timary.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _today: MutableLiveData<String> = MutableLiveData()
    val today: LiveData<String> = _today

    private val _navigateToWrite: MutableLiveData<Event<Unit>> = MutableLiveData()
    val navigateToWrite: LiveData<Event<Unit>> = _navigateToWrite

    private val action = BroadcastChannel<HomeAction>(1)

    init {
        viewModelScope.launch {
            clickWrite().collect {
                _navigateToWrite.value = Event(Unit)
            }
        }
    }

    fun onClickWrite() {
        viewModelScope.launch { action.send(HomeAction.ClickWrite) }
    }

    private fun clickWrite() = action().filterIsInstance<HomeAction.ClickWrite>()

    private fun action() = action.asFlow()
}
