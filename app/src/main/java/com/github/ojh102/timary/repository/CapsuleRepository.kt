package com.github.ojh102.timary.repository

import com.github.ojh102.timary.db.TimaryDB
import com.github.ojh102.timary.model.realm.Capsule
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CapsuleRepository @Inject constructor(
        private val timaryDB: TimaryDB
) {

    fun getHomeCapsuleList(): Observable<List<Capsule>> {
        return timaryDB.getHomeCapsuleList()
    }

    fun getArchiveCapsuleList(): Observable<List<Capsule>> {
        return timaryDB.getArchiveCapsuleList()
    }

    fun findCapsuleById(id: Long): Observable<Capsule> {
        return timaryDB.findCapsuleById(id)
    }

    fun createCapsule(capsule: Capsule): Completable {
        return timaryDB.createCapsule(capsule)
    }

    fun deleteCapsuleById(id: Long): Completable {
        return timaryDB.deleteCapsuleById(id)
    }

}