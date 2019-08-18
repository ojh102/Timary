package com.github.ojh102.timary.ui.write.store

import com.github.ojh102.timary.base.LegacyBaseViewModel
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.repository.CapsuleRepository
import com.github.ojh102.timary.repository.StoreDateRepository
import com.github.ojh102.timary.util.KEY_CAPSULE_CONTENT
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

internal interface StoreContract {

    interface Inputs {
        fun onClickStoreItem(storeItem: StoreItem, position: Int)
        fun storeCapsule(storeItem: StoreItem)
    }

    interface Outputs {
        fun storeDate(): Single<List<StoreItem>>
        fun clickStoreItem(): Observable<Pair<StoreItem, Int>>
        fun completeStoreCapsule(): Observable<Capsule>
    }

    class StoreViewModel @Inject constructor(
        private val storeDateRepository: StoreDateRepository,
        private val capsuleRepository: CapsuleRepository
    ) : LegacyBaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        private val contentRelay = BehaviorRelay.create<String>()
        private val clickStoreItemRelay = PublishRelay.create<Pair<StoreItem, Int>>()
        private val clickStoreCapsuleRelay = PublishRelay.create<Capsule>()

        init {
            intent().map { it.getStringExtra(KEY_CAPSULE_CONTENT) }
                    .subscribeBy { contentRelay.accept(it) }
        }

        override fun storeDate(): Single<List<StoreItem>> {
            return storeDateRepository.getStoreDateList()
        }

        override fun storeCapsule(storeItem: StoreItem) {
            val capsule = Capsule().apply {
                id = System.currentTimeMillis()
                content = contentRelay.value!!
                targetDate = storeItem.date
                writtenDate = System.currentTimeMillis()
            }

            bind(
                    capsuleRepository.createCapsule(capsule)
                            .subscribeBy {
                                clickStoreCapsuleRelay.accept(capsule)
                            }
            )
        }

        override fun completeStoreCapsule(): Observable<Capsule> {
            return clickStoreCapsuleRelay
        }

        override fun onClickStoreItem(storeItem: StoreItem, position: Int) {
            clickStoreItemRelay.accept(storeItem to position)
        }

        override fun clickStoreItem(): Observable<Pair<StoreItem, Int>> {
            return clickStoreItemRelay
        }
    }
}
