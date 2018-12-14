package com.github.ojh102.timary.db

import com.github.ojh102.timary.util.rx.RealmIOTransfer
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import io.realm.*
import java.util.*

object TimaryRealmObservableFactory {

    // Maps for storing strong references to Realm classes while they are subscribed to.
    // This is needed if users create Observables without manually maintaining a reference to them.
    // In that case RealmObjects/RealmResults/RealmLists might be GC'ed too early.
    private val resultsRefs = object : ThreadLocal<StrongReferenceCounter<RealmResults<*>>>() {
        override fun initialValue(): StrongReferenceCounter<RealmResults<*>> {
            return StrongReferenceCounter()
        }
    }

    private val listRefs = object : ThreadLocal<StrongReferenceCounter<RealmList<*>>>() {
        override fun initialValue(): StrongReferenceCounter<RealmList<*>> {
            return StrongReferenceCounter()
        }
    }
    private val objectRefs = object : ThreadLocal<StrongReferenceCounter<RealmModel>>() {
        override fun initialValue(): StrongReferenceCounter<RealmModel> {
            return StrongReferenceCounter()
        }
    }

    private val BACK_PRESSURE_STRATEGY = BackpressureStrategy.LATEST

    fun <E : RealmModel> from(
            realmConfiguration: RealmConfiguration,
            query: (realm: Realm) -> RealmResults<E>
    ): Observable<E> {
        return from(realmConfiguration, query, null)
    }

    fun <E : RealmModel> from(
            realmConfiguration: RealmConfiguration,
            query: (realm: Realm) -> RealmResults<E>,
            defaultValue: E?
    ): Observable<E> {

        return Observable.create<E> { emitter ->
            val observableRealm = Realm.getInstance(realmConfiguration)
            val realmResults: RealmResults<E> = query.invoke(observableRealm)
            resultsRefs.get()!!.acquireReference(realmResults)

            val listener = RealmChangeListener<RealmResults<E>> {
                if(!emitter.isDisposed) {
                    realmResults.first(defaultValue)?.let {
                        emitter.onNext(observableRealm.copyFromRealm(it))
                    }
                }
            }

            realmResults.addChangeListener(listener)

            emitter.setDisposable(Disposables.fromRunnable {
                realmResults.removeChangeListener(listener)
                observableRealm.close()
                resultsRefs.get()!!.releaseReference(realmResults)
            })

            realmResults.first(defaultValue)?.let {
                emitter.onNext(observableRealm.copyFromRealm(it))
            }

        }.compose(RealmIOTransfer())
    }

    fun <E : RealmModel> fromList(
            realmConfiguration: RealmConfiguration,
            query: (realm: Realm) -> RealmResults<E>

    ): Observable<List<E>> {

        return Observable.create<List<E>> { emitter ->

            val observableRealm = Realm.getInstance(realmConfiguration)
            val realmResults = query.invoke(observableRealm)
            resultsRefs.get()!!.acquireReference(realmResults)

            val listener = RealmChangeListener<RealmResults<E>> {
                if(!emitter.isDisposed) {
                    emitter.onNext(observableRealm.copyFromRealm(realmResults))
                }
            }

            realmResults.addChangeListener(listener)

            emitter.setDisposable(Disposables.fromRunnable {
                realmResults.removeChangeListener(listener)
                observableRealm.close()
                resultsRefs.get()!!.releaseReference(realmResults)
            })

            emitter.onNext(observableRealm.copyFromRealm(realmResults))

        }.compose(RealmIOTransfer())
    }

    // Helper class for keeping track of strong references to objects.
    private class StrongReferenceCounter<K> {

        private val references = IdentityHashMap<K, Int>()

        fun acquireReference(obj: K) {
            val count = references[obj]

            if(count == null) {
                references[obj] = 1
            } else {
                references[obj] = count + 1
            }
        }

        fun releaseReference(obj: K) {
            val count = references[obj]

            when {
                count == null -> throw IllegalStateException("Object does not have any references: $obj")
                count > 1 -> references[obj] = count - 1
                count == 1 -> references.remove(obj)
                else -> throw IllegalStateException("Invalid reference count: $count")
            }
        }
    }

}