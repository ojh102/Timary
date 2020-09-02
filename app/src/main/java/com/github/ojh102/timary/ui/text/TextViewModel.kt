package com.github.ojh102.timary.ui.text

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.ojh102.timary.base.BaseViewModel
import javax.inject.Inject

internal class TextViewModel @ViewModelInject constructor() : BaseViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _content = MutableLiveData<String>()
    val content: LiveData<String> = _content

    fun arguments(args: TextFragmentArgs) {
        _title.value = args.title
        _content.value = args.content
    }
}
