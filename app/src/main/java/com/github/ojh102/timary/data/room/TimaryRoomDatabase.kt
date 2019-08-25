package com.github.ojh102.timary.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.ojh102.timary.data.converter.DateConverter
import com.github.ojh102.timary.data.dao.CapsuleDao
import com.github.ojh102.timary.data.entitiy.Capsule

@Database(
    entities = [Capsule::class],
    version = 2
)
@TypeConverters(DateConverter::class)
internal abstract class TimaryRoomDatabase : RoomDatabase() {
    abstract fun capsuleDao(): CapsuleDao
}