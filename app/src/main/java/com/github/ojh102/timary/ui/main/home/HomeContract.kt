package com.github.ojh102.timary.ui.main.home

import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.CapsuleRepository
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject

interface HomeContract {

    interface Inputs {
        fun onClickWrite()
    }

    interface Outputs {
        fun homeCapsuleList(): Observable<List<Capsule>>
        fun clickWrite(): Observable<Unit>
    }

    class HomeViewModel @Inject constructor(
            private val capsuleRepository: CapsuleRepository
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val clickWriteRelay = PublishRelay.create<Unit>()

        override fun homeCapsuleList(): Observable<List<Capsule>> {
            return capsuleRepository.getHomeCapsuleList()
        }

        override fun onClickWrite() {
            clickWriteRelay.accept(Unit)
        }

        override fun clickWrite(): Observable<Unit> {
            return clickWriteRelay
        }
    }
}