package com.github.ojh102.timary.data.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class DateConverter {
    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(value)
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }
}
