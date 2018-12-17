package com.github.ojh102.timary.ui.main.setting

import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import com.github.ojh102.timary.log.TimaryLoggerApi
import com.github.ojh102.timary.repository.SettingRepository
import com.github.ojh102.timary.util.KEY_SETTING_NOTIFICATION
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface SettingContract {

    interface Inputs {
        fun onCheckedAlert(checked: Boolean)
        fun onClickTerm()
        fun onNavigateToTerm()
    }

    interface Outputs {
        fun settingItemList(): Single<List<SettingItems>>
        fun checkedAlert(): Observable<Boolean>
        fun clickTerm(): Observable<Unit>
        fun navigateToTerm(): Observable<Unit>
    }

    class SettingViewModel @Inject constructor(
            private val settingRepository: SettingRepository,
            private val timaryLogger: TimaryLoggerApi,
            private val timarySharedPreferenceManager: TimarySharedPreferenceManager
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val checkedAlertRelay = PublishRelay.create<Boolean>()
        private val clickTermRelay = PublishRelay.create<Unit>()
        private val navigateTermRelay = PublishRelay.create<Unit>()

        init {
            bind(
                    outputs.checkedAlert()
                            .doOnNext { timaryLogger.btnAlert() }
                            .subscribeBy(
                                    onNext = {
                                        timarySharedPreferenceManager.setBoolean(KEY_SETTING_NOTIFICATION, it)
                                    }
                            ),

                    outputs.clickTerm()
                            .throttleFirst(300, TimeUnit.MILLISECONDS)
                            .doOnNext { timaryLogger.btnTerms() }
                            .subscribeBy(
                                    onNext = {
                                       inputs.onNavigateToTerm()
                                    }
                            )
            )
        }

        override fun settingItemList(): Single<List<SettingItems>> {
            return settingRepository.getSettingItemList()
        }

        override fun onCheckedAlert(checked: Boolean) {
            checkedAlertRelay.accept(checked)
        }

        override fun onClickTerm() {
            clickTermRelay.accept(Unit)
        }

        override fun onNavigateToTerm() {
            navigateTermRelay.accept(Unit)
        }

        override fun checkedAlert(): Observable<Boolean> {
            return checkedAlertRelay
        }

        override fun clickTerm(): Observable<Unit> {
            return clickTermRelay
        }

        override fun navigateToTerm(): Observable<Unit> {
            return navigateTermRelay
        }
    }

}