package com.github.ojh102.timary.data.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit

@Entity(tableName = "capsule")
internal data class Capsule(
    val content: String,
    val targetDate: LocalDate,
    val writtenDate: LocalDate,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    fun dDay(): Int {
        return ChronoUnit.DAYS.between(LocalDate.now(), targetDate).toInt()
    }

    fun isOpened(): Boolean {
        return dDay() <= 0
    }
}