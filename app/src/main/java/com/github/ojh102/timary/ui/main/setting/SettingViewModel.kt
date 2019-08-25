package com.github.ojh102.timary.ui.main.setting

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.repository.LocalRepository
import com.github.ojh102.timary.ui.legacy.main.setting.SettingItems
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SettingViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private val _settingItems = MutableLiveData<List<SettingItems>>()
    val settingItems: LiveData<List<SettingItems>> = _settingItems

    private val _navigateToTerm = MutableLiveData<Event<NavDirections>>()
    val navigateToTerm: LiveData<Event<NavDirections>> = _navigateToTerm

    fun loadSettingItems() {
        _settingItems.value = localRepository.getSettingItems()
    }

    fun onClickTerm() {
        _navigateToTerm.value = Event(
            MainFragmentDirections.actionMainFragmentToTextFragment(
                title = context.getString(R.string.setting_term),
                content = context.getString(R.string.term_content)
            )
        )
    }

}
