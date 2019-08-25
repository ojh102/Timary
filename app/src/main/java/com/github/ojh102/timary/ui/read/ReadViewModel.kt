package com.github.ojh102.timary.ui.read

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.ojh102.timary.Event
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.util.extension.dateMemoryWithLineText
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class ReadViewModel @Inject constructor(
    private val context: Context,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private val _capsule = MutableLiveData<Capsule>()
    val capsule: LiveData<Capsule> = _capsule

    private val _showDeleteDialog = MutableLiveData<Event<Unit>>()
    val showDeleteDialog: LiveData<Event<Unit>> = _showDeleteDialog

    private val _navigateToComplete = MutableLiveData<Event<NavDirections>>()
    val navigateToComplete: LiveData<Event<NavDirections>> = _navigateToComplete

    fun loadCapusle(capsuleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.getCapsule(capsuleId).collect {
                launch(Dispatchers.Main) {
                    _capsule.value = it
                }
            }
        }
    }

    fun onClickDelete() {
        _showDeleteDialog.value = Event(Unit)
    }

    fun deleteCapsule(capsuleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.deleteCapsule(capsuleId)

            launch(Dispatchers.Main) {
                _navigateToComplete.value = Event(
                    ReadFragmentDirections.actionReadFragmentToCompleteFragment(
                        context.getString(
                            R.string.format_delete_capsule_title,
                            capsule.value!!.writtenDate.dateMemoryWithLineText()
                        ),
                        null
                    )
                )
            }
        }
    }
}
