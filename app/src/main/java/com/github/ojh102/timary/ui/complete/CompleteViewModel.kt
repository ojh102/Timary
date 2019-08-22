package com.github.ojh102.timary.ui.complete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ojh102.timary.base.BaseViewModel
import javax.inject.Inject

internal class CompleteViewModel @Inject constructor() : BaseViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description
}
