package com.github.ojh102.timary.ui.main.archive

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.repository.LocalRepository
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

internal class ArchiveViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private val _headerText = MutableLiveData<CharSequence>()
    val headerText: LiveData<CharSequence> = _headerText

    private val _capsules = MutableLiveData<List<Capsule>>()
    val capsules: LiveData<List<Capsule>> = _capsules

    private val _navigateToRead = MutableLiveData<Event<NavDirections>>()
    val navigateToRead: LiveData<Event<NavDirections>> = _navigateToRead

    fun loadArchiveCapsules() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getCapsules()
                .collect {
                    launch(Dispatchers.Main) {
                        _headerText.value = SpannableString(context.getString(R.string.format_archive_header, it.size))
                            .apply {
                                setSpan(
                                    TextAppearanceSpan(context, R.style.B16Grape),
                                    0,
                                    it.size.toString().length,
                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                                )
                            }

                        _capsules.value = it
                    }
                }
        }
    }

    fun onClickArchiveCapsule(capsule: Capsule) {
        _navigateToRead.value = Event(MainFragmentDirections.actionMainFragmentToReadFragment())
    }
}
