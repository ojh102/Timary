package com.github.ojh102.timary.ui.write.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.ui.legacy.write.store.StoreItem
import javax.inject.Inject

internal class StoreViewModel @Inject constructor() : BaseViewModel() {
    private val _storeItem = MutableLiveData<StoreItem>()
    val storeItem: LiveData<StoreItem> = _storeItem

    fun storeCapsule() {

    }
}
