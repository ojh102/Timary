package com.github.ojh102.timary.ui.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _today: MutableLiveData<String> = MutableLiveData()
    val today: LiveData<String> = _today

    private val _homeItems = MutableLiveData<List<HomeItems>>()
    val homeItems: LiveData<List<HomeItems>> = _homeItems

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
        _navDirection.value = Event(MainFragmentDirections.actionMainFragmentToWriteFragment())
    }

    fun onClickOpenedCapsule(capsule: Capsule) {
        _navDirection.value = Event(MainFragmentDirections.actionMainFragmentToReadFragment(capsule.id))
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
