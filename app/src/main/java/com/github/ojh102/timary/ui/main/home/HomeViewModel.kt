package com.github.ojh102.timary.ui.main.home

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.repository.LocalRepository
import com.github.ojh102.timary.ui.legacy.main.home.HomeItems
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.filter
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _today: MutableLiveData<String> = MutableLiveData()
    val today: LiveData<String> = _today

    private val _navigateToWrite: MutableLiveData<Event<NavDirections>> = MutableLiveData()
    val navigateToWrite: LiveData<Event<NavDirections>> = _navigateToWrite

    private val _homeItems = MutableLiveData<List<HomeItems>>()
    val homeItems: LiveData<List<HomeItems>> = _homeItems

    init {

    }

    fun loadCapsules() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getCapsules()
                .collect {
                    val items = it.map { capsule ->
                        if (capsule.isOpened()) {
                            HomeItems.StoredCapsule.OpenedCapsule(capsule)
                        } else {
                            HomeItems.StoredCapsule.ClosedCapsule(capsule)
                        }
                    }
                        .toMutableList<HomeItems>().apply {
                            add(0, HomeItems.Header(size))
                        }

                    withContext(Dispatchers.Main) {
                        _homeItems.value = items
                    }
                }
        }
    }

    fun onClickWrite() {
        _navigateToWrite.value = Event(MainFragmentDirections.actionMainFragmentToWriteFragment())
    }

    fun onClickOpenedCapsule(capsule: Capsule) {

    }

    fun onClickClosedCapsule(capsule: Capsule) {
        val diffDay = capsule.dDay()
        val dDay = if (diffDay < 1) {
            1
        } else {
            diffDay.toInt()
        }

        val message = context.getString(R.string.format_click_capsule_close, dDay)

        _toast.value = Event(message)
    }
}
