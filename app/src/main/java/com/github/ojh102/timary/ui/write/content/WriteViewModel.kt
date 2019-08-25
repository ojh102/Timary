package com.github.ojh102.timary.ui.write.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.util.extension.dateMemoryWithLineText
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import javax.inject.Inject

internal class WriteViewModel @Inject constructor() : BaseViewModel() {

    private val _today = MutableLiveData<String>().apply { value = LocalDate.now().dateMemoryWithLineText() }
    val today: LiveData<String> = _today

    private val _navigateToStore = MutableLiveData<Event<NavDirections>>()
    val navigateToStore: LiveData<Event<NavDirections>> = _navigateToStore

    val _contentText = MutableLiveData<String>()
    val contentText = _contentText

    fun clickWrite() {
        viewModelScope.launch {
            contentText.value?.let {
                if (it.isNotBlank()) {
                    _navigateToStore.value = Event(WriteFragmentDirections.actionWriteFragmentToStoreFragment(it))
                }
            }
        }
    }
}
