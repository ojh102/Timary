package com.github.ojh102.timary.db.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

internal abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createOrUpdate(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun createOrUpdate(entity: T)

    @Delete
    abstract fun delete(entity: T)

    @Delete
    abstract fun deletes(entities: List<T>)
}
