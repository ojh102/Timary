package com.github.ojh102.timary.data.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ojh102.timary.util.extension.dDay
import org.threeten.bp.LocalDate

@Entity(tableName = "capsule")
internal data class Capsule(
    val content: String,
    val targetDate: LocalDate,
    val writtenDate: LocalDate,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    fun isOpened(): Boolean {
        return targetDate.dDay() <= 0
    }
}
