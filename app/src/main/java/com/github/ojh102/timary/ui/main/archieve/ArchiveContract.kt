package com.github.ojh102.timary.ui.main.archieve

import com.github.ojh102.timary.base.LegacyBaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.CapsuleRepository
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal interface ArchiveContract {

    interface Inputs {
        fun onClickArchiveCapsule(capsule: Capsule)
        fun onNavigateRead(capsuleId: Long)
    }

    interface Outputs {
        fun archiveCapsuleList(): Observable<List<Capsule>>
        fun clickArchiveCapsule(): Observable<Capsule>
        fun navigateRead(): Observable<Long>
    }

    class ArchiveViewModel @Inject constructor(
        private val capsuleRepository: CapsuleRepository
    ) : LegacyBaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val clickArchiveRelay = PublishRelay.create<Capsule>()
        private val navigateReadRelay = PublishRelay.create<Long>()

        init {
            bind(
                    outputs.clickArchiveCapsule()
                            .throttleFirst(300, TimeUnit.MILLISECONDS)
                            .subscribeBy(
                                    onNext = {
                                        inputs.onNavigateRead(it.id)
                                    }
                            )
            )
        }

        override fun archiveCapsuleList(): Observable<List<Capsule>> {
            return capsuleRepository.getArchiveCapsuleList()
        }

        override fun onClickArchiveCapsule(capsule: Capsule) {
            clickArchiveRelay.accept(capsule)
        }

        override fun onNavigateRead(capsuleId: Long) {
            navigateReadRelay.accept(capsuleId)
        }

        override fun clickArchiveCapsule(): Observable<Capsule> {
            return clickArchiveRelay
        }

        override fun navigateRead(): Observable<Long> {
            return navigateReadRelay
        }
    }
}
