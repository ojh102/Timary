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
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ArchiveViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private val _headerText = MutableLiveData<CharSequence>()
    val headerText: LiveData<CharSequence> = _headerText

    private val _archiveItems = MutableLiveData<List<ArchiveItems>>()
    val archiveItems: LiveData<List<ArchiveItems>> = _archiveItems

    private val _navigateToRead = MutableLiveData<Event<NavDirections>>()
    val navigateToRead: LiveData<Event<NavDirections>> = _navigateToRead

    fun loadArchiveCapsules() {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getArchivedCapsules()
                .map { createArchiveItems(it) }
                .collect {
                    launch(Dispatchers.Main) {
                        _headerText.value = getHeaderText(it.size)
                        _archiveItems.value = it
                    }
                }
        }
    }

    fun onClickArchiveCapsule(item: ArchiveItems.ArchiveItem) {
        _navigateToRead.value = Event(MainFragmentDirections.actionMainFragmentToReadFragment(item.capsule.id))
    }

    private fun createArchiveItems(capsules: List<Capsule>): List<ArchiveItems> {
        return capsules.map { ArchiveItems.ArchiveItem(it) }
    }

    private fun getHeaderText(size: Int): CharSequence {
        return SpannableString(context.getString(R.string.format_archive_header, size))
            .apply {
                setSpan(
                    TextAppearanceSpan(context, R.style.B16Grape),
                    0,
                    size.toString().length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
    }
}
