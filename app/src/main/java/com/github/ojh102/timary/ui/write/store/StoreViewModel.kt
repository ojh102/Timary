package com.github.ojh102.timary.ui.write.store

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.util.extension.completeWriteText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

internal class StoreViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository

) : BaseViewModel() {

    private val _content = MutableLiveData<String>()
    val content: LiveData<String> = _content

    private val _storeItems = MutableLiveData<List<StoreItem>>().apply { value = localRepository.storeItems() }
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
            val capsule = Capsule(
                content = storeContent,
                targetDate = it.date,
                writtenDate = LocalDate.now()
            )

            GlobalScope.launch(Dispatchers.IO) { localRepository.createOrUpdateCapsule(capsule) }

            _navigateToComplete.value = Event(
                StoreFragmentDirections.actionStoreFragmentToCompleteFragment(
                    capsule.targetDate.completeWriteText(),
                    null
                )
            )
        }
    }

    fun onClickStoreItem(item: StoreItem, position: Int) {
        if (position == 0) {
            _showDatePicker.value = Event(Unit)

            return
        }

        _currentStoreItem.value = item
    }

    fun onSelectDatePicker(localDate: LocalDate) {
        _currentStoreItem.value = StoreItem(context.getString(R.string.store_calendar_selected), localDate)
    }
}
