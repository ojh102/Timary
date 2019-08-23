package com.github.ojh102.timary.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.repository.LocalRepository
import com.github.ojh102.timary.ui.legacy.main.home.HomeItems
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(

    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _today: MutableLiveData<String> = MutableLiveData()
    val today: LiveData<String> = _today

    private val _navigateToWrite: MutableLiveData<Event<NavDirections>> = MutableLiveData()
    val navigateToWrite: LiveData<Event<NavDirections>> = _navigateToWrite

    private val _homeItems = MutableLiveData<List<HomeItems>>()
    val homeItems: LiveData<List<HomeItems>> = _homeItems

    private val action = BroadcastChannel<HomeAction>(1)

    init {
        viewModelScope.launch {
            clickWrite().collect {
                _navigateToWrite.value = Event(MainFragmentDirections.actionMainFragmentToWriteFragment())
            }
        }
    }

    fun loadCapsules() {
        viewModelScope.launch {
            _homeItems.value = withContext(Dispatchers.IO) {
                localRepository.getCapsules().map { capsule ->
                    if (capsule.isOpened()) {
                        HomeItems.StoredCapsule.OpenedCapsule(capsule)
                    } else {
                        HomeItems.StoredCapsule.ClosedCapsule(capsule)
                    }
                }.toMutableList<HomeItems>().apply {
                    add(0, HomeItems.Header(size))
                }
            }
        }
    }

    fun onClickWrite() {
        viewModelScope.launch { action.send(HomeAction.ClickWrite) }
    }

    private fun clickWrite() = action().filterIsInstance<HomeAction.ClickWrite>()

    private fun action() = action.asFlow()
}
