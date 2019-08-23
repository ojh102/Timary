package com.github.ojh102.timary.ui.write.store

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.StoreDateRepository
import com.github.ojh102.timary.ui.legacy.complete.CompleteType
import com.github.ojh102.timary.ui.legacy.write.store.StoreItem
import com.github.ojh102.timary.util.TimaryParser
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

internal class StoreViewModel @Inject constructor(
    private val storeDateRepository: StoreDateRepository,
    private val context: Context,
    private val timaryParser: TimaryParser

) : BaseViewModel() {

    private val _content = MutableLiveData<String>()
    val content: LiveData<String> = _content

    private val _storeItems = MutableLiveData<List<StoreItem>>().apply { value = storeDateRepository.storeDateList() }
    val storeItems: LiveData<List<StoreItem>> = _storeItems

    private val _currentStoreItem = MutableLiveData<StoreItem>()
    val currentStoreItem: LiveData<StoreItem> = _currentStoreItem

    private val _showDatePicker = MutableLiveData<Event<Unit>>()
    val showDatePicker: LiveData<Event<Unit>> = _showDatePicker

    private val _navigateToComplete = MutableLiveData<Event<NavDirections>>()
    val navigateToComplete: LiveData<Event<NavDirections>> = _navigateToComplete

    fun argument(argument: StoreFragmentArgs) {
        _content.value = argument.content
    }

    fun storeCapsule() {
        val storeContent = content.value!!

        currentStoreItem.value?.let {
            val capsule = Capsule().apply {
                id = System.currentTimeMillis()
                content = storeContent
                targetDate = it.date
                writtenDate = System.currentTimeMillis()
            }

            viewModelScope.launch {
                _navigateToComplete.value = Event(
                    StoreFragmentDirections.actionStoreFragmentToCompleteFragment(
                        CompleteType.WRITE,
                        timaryParser.completeWriteText(capsule.targetDate),
                        null
                    )
                )
            }
        }
    }

    fun onClickStoreItem(item: StoreItem, position: Int) {
        if (position == 0) {
            _showDatePicker.value = Event(Unit)

            return
        }

        _currentStoreItem.value = item
    }

    fun onSelectDatePicker(calendar: Calendar) {
        _currentStoreItem.value = StoreItem(context.getString(R.string.store_calendar_selected), calendar.timeInMillis)
    }
}
