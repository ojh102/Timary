package com.github.ojh102.timary.ui.write.content

import com.github.ojh102.timary.base.LegacyBaseViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject

interface WriteContract {

    interface Inputs {
        fun inputContent(content: String)
        fun onClickWrite()
    }

    interface Outputs {
        fun outputContent(): Observable<String>
        fun clickWrite(): Observable<Unit>
    }

    class WriteViewModel @Inject constructor() : LegacyBaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val contentRelay = BehaviorRelay.createDefault<String>("")
        private val clickWriteRelay = PublishRelay.create<Unit>()

        override fun inputContent(content: String) {
            contentRelay.accept(content)
        }

        override fun outputContent(): Observable<String> {
            return contentRelay
        }

        override fun onClickWrite() {
            clickWriteRelay.accept(Unit)
        }

        override fun clickWrite(): Observable<Unit> {
            return clickWriteRelay
        }
    }
}
