package com.github.ojh102.timary.data.converter

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

internal class DateConverter {
    @TypeConverter
    fun fromLocaleDate(value: LocalDate): String {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(value)
    }

    @TypeConverter
    fun toLocaleDate(value: String): LocalDate {
        return DateTimeFormatter.ISO_LOCAL_DATE.parse(value, LocalDate.FROM)
    }
}
