package com.github.ojh102.timary.db.room

import androidx.room.Dao
import androidx.room.Query
import com.github.ojh102.timary.model.Capsule
import io.reactivex.Flowable

@Dao
internal abstract class CapsuleDao : BaseDao<Capsule>() {
    @Query("SELECT * FROM capsule WHERE id = :id")
    abstract fun get(id: Long): Capsule

    @Query("SELECT * FROM capsule ORDER BY targetDate DESC")
    abstract fun gets(): List<Capsule>

    @Query("DELETE FROM capsule WHERE id = :id")
    abstract fun delete(id: Long)
}