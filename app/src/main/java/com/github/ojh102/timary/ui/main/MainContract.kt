package com.github.ojh102.timary.ui.main

import com.github.ojh102.timary.base.BaseViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject

interface MainContract {

    interface Inputs {
        fun onClickHome()
        fun onClickArchive()
        fun onClickSetting()
    }

    interface Outputs {
        fun clickHome(): Observable<Unit>
        fun clickArchive(): Observable<Unit>
        fun clickSetting(): Observable<Unit>
    }

    class MainViewModel @Inject constructor() : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val clickHomeRelay = PublishRelay.create<Unit>()
        private val clickArchiveRelay = PublishRelay.create<Unit>()
        private val clickSettingRelay = PublishRelay.create<Unit>()

        override fun onClickHome() {
            clickHomeRelay.accept(Unit)
        }

        override fun onClickArchive() {
            clickArchiveRelay.accept(Unit)
        }

        override fun onClickSetting() {
            clickSettingRelay.accept(Unit)
        }

        override fun clickHome(): Observable<Unit> {
            return clickHomeRelay
        }

        override fun clickArchive(): Observable<Unit> {
            return clickArchiveRelay
        }

        override fun clickSetting(): Observable<Unit> {
            return clickSettingRelay
        }
    }
}
