package com.github.ojh102.timary.ui.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import com.github.ojh102.timary.util.TimaryParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val context: Context,
    private val timaryParser: TimaryParser,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _today = MutableLiveData<String>()
    val today: LiveData<String> = _today

    private val _homeItems = MutableLiveData<List<HomeItems>>()
    val homeItems: LiveData<List<HomeItems>> = _homeItems

    fun loadCapsules() {
        _today.value = timaryParser.dateToTitleWithLine(LocalDate.now())

        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getHomeCapsules()
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
        _toast.value = Event(context.getString(R.string.format_click_capsule_close, capsule.dDay()))
    }
}
