package com.github.ojh102.timary.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.github.ojh102.timary.base.BaseViewModel
import javax.inject.Inject

internal class HomeViewModel @Inject constructor() : BaseViewModel() {
    val today = MutableLiveData<String>()

    fun onClickWrite() {

    }
}
