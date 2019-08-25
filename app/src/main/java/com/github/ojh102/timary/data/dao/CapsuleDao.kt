package com.github.ojh102.timary.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.ojh102.timary.data.entitiy.Capsule
import kotlinx.coroutines.flow.Flow

@Dao
internal abstract class CapsuleDao : BaseDao<Capsule>() {
    @Query("SELECT * FROM capsule WHERE id = :id")
    abstract fun get(id: Long): Flow<Capsule>

    @Query("SELECT * FROM capsule ORDER BY targetDate DESC")
    abstract fun gets(): Flow<List<Capsule>>

    @Query("DELETE FROM capsule WHERE id = :id")
    abstract fun delete(id: Long)
}