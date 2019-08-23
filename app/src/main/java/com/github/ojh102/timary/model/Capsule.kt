package com.github.ojh102.timary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "capsule")
internal data class Capsule(
    val content: String,
    val targetDate: Long,
    val writtenDate: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    fun dDay(): Float {
        val diff = targetDate - System.currentTimeMillis()
        val diffDay = (diff.toFloat() / (24 * 60 * 60 * 1000))

        return diffDay
    }

    fun isOpened(): Boolean {
        return dDay() <= 0f
    }
}