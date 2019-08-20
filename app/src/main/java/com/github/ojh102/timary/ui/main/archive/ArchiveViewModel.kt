package com.github.ojh102.timary.ui.main.archive

import androidx.lifecycle.MutableLiveData
import com.github.ojh102.timary.base.BaseViewModel
import javax.inject.Inject

internal class ArchiveViewModel @Inject constructor() : BaseViewModel() {
    val headerText = MutableLiveData<CharSequence>()
}
