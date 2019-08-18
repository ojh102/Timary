package com.github.ojh102.timary.db

import com.github.ojh102.timary.model.realm.Capsule
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

internal class TimaryDB @Inject constructor() {

    fun getHomeCapsuleList(): Observable<List<Capsule>> {
        return Observable.empty()
    }

    fun getArchiveCapsuleList(): Observable<List<Capsule>> {
        return Observable.empty()
    }

    fun findCapsuleById(id: Long): Observable<Capsule> {
        return Observable.empty()
    }

    fun createCapsule(capsule: Capsule): Completable {
        return Completable.never()
    }

    fun deleteCapsuleById(id: Long): Completable {
        return Completable.never()
    }
}
