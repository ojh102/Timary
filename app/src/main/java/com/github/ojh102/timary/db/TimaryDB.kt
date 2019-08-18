package com.github.ojh102.timary.db

import com.github.ojh102.timary.model.realm.Capsule
import io.reactivex.Completable
import io.reactivex.Observable
import io.realm.RealmConfiguration
import javax.inject.Inject

class TimaryDB @Inject constructor(
    private val realmConfiguration: RealmConfiguration
) {

    fun getHomeCapsuleList(): Observable<List<Capsule>> {
        return TimaryRealmObservableFactory.fromList(realmConfiguration) {
            val curMillis = System.currentTimeMillis()
            val hourMillis = curMillis % (24 * 60 * 60 * 1000)
            val curTime = curMillis - hourMillis

            it.where(Capsule::class.java).greaterThanOrEqualTo(FIELD_TARGET_DATE, curTime).sort(FIELD_TARGET_DATE).findAll()
        }
    }

    fun getArchiveCapsuleList(): Observable<List<Capsule>> {
        return TimaryRealmObservableFactory.fromList(realmConfiguration) {
            val curMillis = System.currentTimeMillis()
            val hourMillis = curMillis % (24 * 60 * 60 * 1000)
            val curTime = curMillis - hourMillis

            it.where(Capsule::class.java).lessThanOrEqualTo(FIELD_TARGET_DATE, curTime).sort(FIELD_TARGET_DATE).findAll()
        }
    }

    fun findCapsuleById(id: Long): Observable<Capsule> {
        return TimaryRealmObservableFactory.from(realmConfiguration) {
            it.where(Capsule::class.java).equalTo(FIELD_ID, id).findAll()
        }
    }

    fun createCapsule(capsule: Capsule): Completable {
        return DBTransaction.executeAsync(realmConfiguration) {
            it.insertOrUpdate(capsule)
        }
    }

    fun deleteCapsuleById(id: Long): Completable {
        return DBTransaction.executeAsync(realmConfiguration) {
            it.where(Capsule::class.java).equalTo(FIELD_ID, id).findFirst()?.deleteFromRealm()
        }
    }
}
