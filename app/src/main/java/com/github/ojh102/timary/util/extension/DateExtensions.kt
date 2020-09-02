package com.github.ojh102.timary.util.extension

import com.github.ojh102.timary.R
import com.github.ojh102.timary.util.ResourcesProvider
import com.github.ojh102.timary.util.ResourcesUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

enum class Season(val month: Int, val day: Int) {
    SPRING(1, 4),
    SUMMER(4, 5),
    AUTUMN(7, 7),
    WINTER(10, 7);
}

internal fun LocalDate.yyMMdd(): String {
    return DateTimeFormatter.ofPattern(ResourcesUtil.getString(R.string.format_date), Locale.KOREAN).format(this)
}

internal fun LocalDate.yearText(): String {
    return String.format(ResourcesUtil.getString(R.string.format_year), this.year)
}

internal fun LocalDate.dateMemoryText(): String {
    val month = monthValue
    val day = dayOfMonth

    val dateText = if (month == 0 && day == 1) {
        ResourcesUtil.getString(R.string.capsule_first_day)
    } else if (month == 11 && day == 31) {
        ResourcesUtil.getString(R.string.capsule_last_day)
    } else {
        monthDayText()
    }

    return String.format(ResourcesUtil.getString(R.string.format_capsule_title_format), dateText)
}

internal fun LocalDate.dateMemoryWithLineText(resourcesProvider: ResourcesProvider): String {
    return DateTimeFormatter
        .ofPattern(resourcesProvider.getString(R.string.format_date_title_memory_with_line), Locale.KOREAN)
        .format(this)
}

internal fun LocalDate.completeWriteText(): String {
    return String.format(ResourcesUtil.getString(R.string.format_write_capsule_title), yyMMdd())
}

internal fun LocalDate.dDayCountText(): String {
    val diffDay = dDay()

    val dDay = when {
        diffDay <= 0 -> ResourcesUtil.getString(R.string.today)
        else -> diffDay.toString()
    }

    return String.format(ResourcesUtil.getString(R.string.format_dday), getTextFromEventDay(this), dDay)
}

internal fun LocalDate.monthDayText(): String {
    return DateTimeFormatter.ofPattern(ResourcesUtil.getString(R.string.format_date_month_day), Locale.KOREAN).format(this)
}

internal fun LocalDate.dDay(startDay: LocalDate = LocalDate.now()): Int {
    return ChronoUnit.DAYS.between(startDay, this).toInt()
}

internal fun archiveText(targetDate: LocalDate): String {
    return String.format(ResourcesUtil.getString(R.string.format_dday_archive), getTextFromEventDay(targetDate))
}

private fun getTextFromEventDay(targetDate: LocalDate): String {
    val targetMonth = targetDate.month.value
    val targetDay = targetDate.dayOfMonth

    return if (targetMonth == Season.SPRING.month && targetDay == Season.SPRING.day) {
        ResourcesUtil.getString(R.string.store_spring)
    } else if (targetMonth == Season.SUMMER.month && targetDay == Season.SUMMER.day) {
        ResourcesUtil.getString(R.string.store_summer)
    } else if (targetMonth == Season.AUTUMN.month && targetDay == Season.AUTUMN.day) {
        ResourcesUtil.getString(R.string.store_autumn)
    } else if (targetMonth == Season.WINTER.month && targetDay == Season.WINTER.day) {
        ResourcesUtil.getString(R.string.store_winter)
    } else if (targetMonth == 0 && targetDay == 1) {
        ResourcesUtil.getString(R.string.store_first_day)
    } else if (targetMonth == 11 && targetDay == 31) {
        ResourcesUtil.getString(R.string.store_last_day)
    } else {
        targetDate.monthDayText()
    }
}

internal fun Season.localDate(): LocalDate {
    val now = LocalDate.now()

    val targetYear = if (now.monthValue >= month && now.dayOfMonth >= day) {
        now.year + 1
    } else {
        now.year
    }

    return LocalDate.of(targetYear, month + 1, day)
}
