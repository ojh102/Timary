package com.github.ojh102.timary.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import com.github.ojh102.timary.util.ResourcesProvider
import com.github.ojh102.timary.util.extension.dDay
import com.github.ojh102.timary.util.extension.dateMemoryWithLineText
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate

internal class HomeViewModel @Inject constructor(
    private val resourcesProvider: ResourcesProvider,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _today = MutableLiveData<String>()
    val today: LiveData<String> = _today

    private val _homeItems = MutableLiveData<List<HomeItems>>()
    val homeItems: LiveData<List<HomeItems>> = _homeItems

    fun loadCapsules() {
        _today.value = LocalDate.now().dateMemoryWithLineText(resourcesProvider)

        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getHomeCapsules()
                .map { createHomeItems(it) }
                .collect {
                    launch(Dispatchers.Main) {
                        _homeItems.value = it
                    }
                }
        }
    }

    fun onClickWrite() {
        navDirections.value = Event(MainFragmentDirections.actionMainFragmentToWriteFragment())
    }

    fun onClickOpenedCapsule(item: HomeItems.StoredCapsule.OpenedCapsule) {
        navDirections.value = Event(MainFragmentDirections.actionMainFragmentToReadFragment(item.capsule.id))
    }

    fun onClickClosedCapsule(item: HomeItems.StoredCapsule.ClosedCapsule) {
        toast.value = Event(String.format(resourcesProvider.getString(R.string.format_click_capsule_close), item.capsule.targetDate.dDay()))
    }

    private fun createHomeItems(capsules: List<Capsule>): List<HomeItems> {
        return capsules.map { capsule ->
            if (capsule.isOpened()) {
                HomeItems.StoredCapsule.OpenedCapsule(capsule)
            } else {
                HomeItems.StoredCapsule.ClosedCapsule(capsule)
            }
        }
            .toMutableList<HomeItems>().apply {
                add(0, HomeItems.Header(size))
            }
    }
}
