package com.github.ojh102.timary.ui.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ojh102.timary.base.BaseViewModel
import javax.inject.Inject

internal class WriteViewModel @Inject constructor() : BaseViewModel() {
    private val _today: MutableLiveData<String> = MutableLiveData()
    val today: LiveData<String> = _today

    fun onContentTextChanged(content: String) {

    }
}
