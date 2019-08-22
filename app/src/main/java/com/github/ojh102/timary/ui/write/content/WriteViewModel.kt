package com.github.ojh102.timary.ui.write.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.util.TimaryParser
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

internal class WriteViewModel @Inject constructor(
    val timaryParser: TimaryParser
) : BaseViewModel() {

    private val _today = MutableLiveData<String>().apply { }
    val today: LiveData<String> = _today

    private val _navigateToStore = MutableLiveData<Event<String>>()
    val navigateToStore: LiveData<Event<String>> = _navigateToStore

    val contentText = ConflatedBroadcastChannel<String>()

    init {
        _today.apply { timaryParser.dateToTitleWithLine(Date().time) }
    }

    fun onContentTextChanged(content: String) {
        viewModelScope.launch { contentText.send(content) }
    }

    fun clickWrite() {
        viewModelScope.launch {
            contentText.consumeEach {
                _navigateToStore.value = Event(it)
            }
        }
    }
}
