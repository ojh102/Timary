package com.github.ojh102.timary.ui.main.home

import android.content.res.Resources
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.CapsuleRepository
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface HomeContract {

    interface Inputs {
        fun onClickWrite()
        fun onClickClosedCapsule(capsule: Capsule)
        fun onClickOpenedCapsule(capsule: Capsule)
        fun onToast(message: String)
        fun onNavigateToRead(capsuleId: Long)
        fun onNavigateToWrite()
    }

    interface Outputs {
        fun homeItemList(): Observable<List<HomeItems>>
        fun clickWrite(): Observable<Unit>
        fun clickClosedCapsule(): Observable<Capsule>
        fun clickOpenedCapsule(): Observable<Capsule>
        fun toast(): Observable<String>
        fun navigateToRead(): Observable<Long>
        fun navigateToWrite(): Observable<Unit>
    }

    class HomeViewModel @Inject constructor(
        private val capsuleRepository: CapsuleRepository,
        private val resources: Resources
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val clickWriteRelay = PublishRelay.create<Unit>()
        private val clickClosedCapsuleRelay = PublishRelay.create<Capsule>()
        private val clickOpenedCapsuleRelay = PublishRelay.create<Capsule>()

        private val toastRelay = PublishRelay.create<String>()
        private val navigateToReadRelay = PublishRelay.create<Long>()
        private val navigateToWriteRelay = PublishRelay.create<Unit>()

        init {
            bind(
                    clickWrite()
                            .throttleFirst(300, TimeUnit.MILLISECONDS)
                            .subscribeBy(
                                    onNext = {
                                        inputs.onNavigateToWrite()
                                    }
                            ),

                    clickClosedCapsule()
                            .throttleFirst(300, TimeUnit.MILLISECONDS)
                            .subscribeBy(
                                    onNext = {
                                        val diffDay = it.dDay()
                                        val dDay = if (diffDay < 1) {
                                            1
                                        } else {
                                            diffDay.toInt()
                                        }

                                        val message = resources.getString(R.string.format_click_capsule_close, dDay)

                                        inputs.onToast(message)
                                    }
                            ),

                    clickOpenedCapsule()
                            .throttleFirst(300, TimeUnit.MILLISECONDS)
                            .subscribeBy(
                                    onNext = {
                                        inputs.onNavigateToRead(it.id)
                                    }
                            )
            )
        }

        override fun homeItemList(): Observable<List<HomeItems>> {
            return capsuleRepository.getHomeCapsuleList().map { capsuleList ->
                capsuleList.map { capsule ->
                    if (capsule.isOpened()) {
                        HomeItems.StoredCapsule.OpenedCapsule(capsule)
                    } else {
                        HomeItems.StoredCapsule.ClosedCapsule(capsule)
                    }
                }.toMutableList<HomeItems>().apply {
                    add(0, HomeItems.Header(size))
                }
            }
        }

        override fun onClickWrite() {
            clickWriteRelay.accept(Unit)
        }

        override fun onClickClosedCapsule(capsule: Capsule) {
            clickClosedCapsuleRelay.accept(capsule)
        }

        override fun onClickOpenedCapsule(capsule: Capsule) {
            clickOpenedCapsuleRelay.accept(capsule)
        }

        override fun onToast(message: String) {
            toastRelay.accept(message)
        }

        override fun onNavigateToRead(capsuleId: Long) {
            navigateToReadRelay.accept(capsuleId)
        }

        override fun onNavigateToWrite() {
            navigateToWriteRelay.accept(Unit)
        }

        override fun clickWrite(): Observable<Unit> {
            return clickWriteRelay
        }

        override fun clickClosedCapsule(): Observable<Capsule> {
            return clickClosedCapsuleRelay
        }

        override fun clickOpenedCapsule(): Observable<Capsule> {
            return clickOpenedCapsuleRelay
        }

        override fun toast(): Observable<String> {
            return toastRelay
        }

        override fun navigateToRead(): Observable<Long> {
            return navigateToReadRelay
        }

        override fun navigateToWrite(): Observable<Unit> {
            return navigateToWriteRelay
        }
    }
}
