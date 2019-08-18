package com.github.ojh102.timary.ui.read

import com.github.ojh102.timary.base.LegacyBaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.CapsuleRepository
import com.github.ojh102.timary.util.KEY_CAPSULE_ID
import com.github.ojh102.timary.util.TimaryParser
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

interface ReadContract {

    interface Inputs {
        fun onClickDelete()
        fun deleteCapsule()
    }

    interface Outputs {
        fun capsule(): Observable<Capsule>
        fun clickDelete(): Observable<Unit>
        fun completeDeleteCapsule(): Observable<String>
    }

    class ReadViewModel @Inject constructor(
        private val capsuleRepository: CapsuleRepository,
        private val timaryParser: TimaryParser
    ) : LegacyBaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val capsuleRelay = BehaviorRelay.create<Capsule>()
        private val clickDeleteRelay = PublishRelay.create<Unit>()
        private val completeDeleteCapsuleRelay = PublishRelay.create<String>()

        init {
            intent().map { it.getLongExtra(KEY_CAPSULE_ID, 0) }.filter { it != 0L }
                    .flatMap { capsuleRepository.findCapsuleById(it) }
                    .subscribeBy {
                        capsuleRelay.accept(it)
                    }
        }

        override fun capsule(): Observable<Capsule> {
            return capsuleRelay
        }

        override fun onClickDelete() {
            clickDeleteRelay.accept(Unit)
        }

        override fun clickDelete(): Observable<Unit> {
            return clickDeleteRelay
        }

        override fun deleteCapsule() {
            bind(
                    capsuleRepository.deleteCapsuleById(capsuleRelay.value!!.id)
                            .subscribeBy {
                                completeDeleteCapsuleRelay.accept(timaryParser.dateToTitleWithLine(capsuleRelay.value!!.writtenDate))
                            }
            )
        }

        override fun completeDeleteCapsule(): Observable<String> {
            return completeDeleteCapsuleRelay
        }
    }
}
