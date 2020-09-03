package com.github.ojh102.timary.ui.setting

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import dagger.hilt.android.qualifiers.ApplicationContext

internal class SettingViewModel @ViewModelInject constructor(
    @ApplicationContext private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private val _settingItems = MutableLiveData<List<SettingItems>>()
    val settingItems: LiveData<List<SettingItems>> = _settingItems

    private val _navigateToTerm = MutableLiveData<Event<NavDirections>>()
    val navigateToTerm: LiveData<Event<NavDirections>> = _navigateToTerm

    fun loadSettingItems() {
        _settingItems.value = localRepository.settingItems()
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
