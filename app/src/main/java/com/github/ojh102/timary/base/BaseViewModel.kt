package com.github.ojh102.timary.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ojh102.timary.Event
import io.reactivex.disposables.CompositeDisposable

internal abstract class BaseViewModel : ViewModel() {
    protected val _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>> = _toast
}
