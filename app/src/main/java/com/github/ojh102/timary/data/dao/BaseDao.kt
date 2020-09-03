package com.github.ojh102.timary.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

internal interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdate(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdate(entity: T)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun deletes(entities: List<T>)
}
