package com.github.ojh102.timary.ui.complete

import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.util.KEY_COMPLETE_DESCRIPTION
import com.github.ojh102.timary.util.KEY_COMPLETE_TITLE
import com.github.ojh102.timary.util.KEY_COMPLETE_TYPE
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

interface CompleteContract {

    interface Inputs

    interface Outputs {
        fun title(): Observable<String>
        fun description(): Observable<String>
        fun type(): Observable<CompleteType>
    }

    class CompleteViewModel @Inject constructor() : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val titleRelay = BehaviorRelay.create<String>()
        private val descriptionRelay = BehaviorRelay.create<String>()
        private val typeRelay = BehaviorRelay.create<CompleteType>()

        init {
            intent()
                    .subscribeBy {
                        typeRelay.accept(it.getSerializableExtra(KEY_COMPLETE_TYPE) as CompleteType)
                        titleRelay.accept(it.getStringExtra(KEY_COMPLETE_TITLE))

                        it.getStringExtra(KEY_COMPLETE_DESCRIPTION)?.let {
                            this.descriptionRelay.accept(it)
                        }
                    }
        }

        override fun title(): Observable<String> {
            return titleRelay
        }

        override fun description(): Observable<String> {
            return descriptionRelay
        }

        override fun type(): Observable<CompleteType> {
            return typeRelay
        }
    }
}
