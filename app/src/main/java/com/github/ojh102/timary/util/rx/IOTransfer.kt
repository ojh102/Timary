package com.github.ojh102.timary.util.rx

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class IOTransfer<T> : SingleTransformer<T, T>, ObservableTransformer<T, T>, FlowableTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}

class CompletableIOTransfer : CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}