package com.github.ojh102.timary.ui.main.archieve

import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.CapsuleRepository
import io.reactivex.Observable
import javax.inject.Inject

interface ArchiveContract {

    interface Inputs

    interface Outputs {
        fun archiveCapsuleList(): Observable<List<Capsule>>
    }

    class ArchiveViewModel @Inject constructor(
            private val capsuleRepository: CapsuleRepository
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        override fun archiveCapsuleList(): Observable<List<Capsule>> {
            return capsuleRepository.getArchiveCapsuleList()
        }
    }
}