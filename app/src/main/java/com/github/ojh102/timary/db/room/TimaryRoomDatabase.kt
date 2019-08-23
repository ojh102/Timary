package com.github.ojh102.timary.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ojh102.timary.model.Capsule

@Database(
    entities = [Capsule::class],
    version = 1
)
internal abstract class TimaryRoomDatabase : RoomDatabase() {
    abstract fun capsuleDao(): CapsuleDao
}