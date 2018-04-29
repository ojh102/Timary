package com.github.ojh102.timary.util.rx

import io.reactivex.*
import org.reactivestreams.Publisher

class RealmIOTransfer<T> : ObservableTransformer<T, T>, FlowableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(TimarySchedulers.realmIO())
                .unsubscribeOn(TimarySchedulers.realmIO())
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(TimarySchedulers.realmIO())
                .unsubscribeOn(TimarySchedulers.realmIO())
    }

}
