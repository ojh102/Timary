package com.github.ojh102.timary.ui.write

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.util.ResourcesProvider
import com.github.ojh102.timary.util.extension.dateMemoryWithLineText
import java.time.LocalDate
import javax.inject.Inject

internal class WriteViewModel @ViewModelInject constructor(
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

    private val _today = MutableLiveData<String>().apply { value = LocalDate.now().dateMemoryWithLineText(resourcesProvider) }
    val today: LiveData<String> = _today

    private val _navigateToStore = MutableLiveData<Event<NavDirections>>()
    val navigateToStore: LiveData<Event<NavDirections>> = _navigateToStore

    val contentText = MutableLiveData<String>()

    fun onClickWrite() {
        contentText.value?.let {
            if (it.isNotBlank()) {
                _navigateToStore.value = Event(WriteFragmentDirections.actionWriteFragmentToStoreFragment(it))
            }
        }
    }
}
