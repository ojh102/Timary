package com.github.ojh102.timary.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import java.util.Locale
import javax.inject.Inject

class TimaryParser @Inject constructor(private val context: Context) {

    fun dateToYear(date: LocalDate): String {
        return context.getString(R.string.format_year, date.year)
    }

    fun dateToCapsuleTitle(writtenDate: LocalDate): String {
        val month = writtenDate.monthValue
        val day = writtenDate.dayOfMonth

        val datText = if (month == 0 && day == 1) {
            context.getString(R.string.capsule_first_day)
        } else if (month == 11 && day == 31) {
            context.getString(R.string.capsule_last_day)
        } else {
            dateToTextMonthDay(writtenDate)
        }

        return context.getString(R.string.format_capsule_title_format, datText)
    }

    fun dDay(targetDate: LocalDate): Int {
        return ChronoUnit.DAYS.between(LocalDate.now(), targetDate).toInt()
    }

    fun dateToCapsuleDday(targetDate: LocalDate): String {
        val diffDay = dDay(targetDate)

        val dDay = when {
            diffDay <= 0 -> context.getString(R.string.today)
            else -> diffDay.toString()
        }

        return context.getString(R.string.format_dday, getTextFromEventDay(targetDate), dDay)
    }

    fun dateToArchiveDay(targetDate: LocalDate): String {
        return context.getString(R.string.format_dday_archive, getTextFromEventDay(targetDate))
    }

    fun getTextFromEventDay(targetDate: LocalDate): String {
        val targetMonth = targetDate.month.value
        val targetDay = targetDate.dayOfMonth

        return if (targetMonth == Season.SPRING.month && targetDay == Season.SPRING.day) {
            context.getString(R.string.store_spring)
        } else if (targetMonth == Season.SUMMER.month && targetDay == Season.SUMMER.day) {
            context.getString(R.string.store_summer)
        } else if (targetMonth == Season.AUTUMN.month && targetDay == Season.AUTUMN.day) {
            context.getString(R.string.store_autumn)
        } else if (targetMonth == Season.WINTER.month && targetDay == Season.WINTER.day) {
            context.getString(R.string.store_winter)
        } else if (targetMonth == 0 && targetDay == 1) {
            context.getString(R.string.store_first_day)
        } else if (targetMonth == 11 && targetDay == 31) {
            context.getString(R.string.store_last_day)
        } else {
            dateToTextMonthDay(targetDate)
        }
    }

    fun dateToTitleWithLine(writtenDate: LocalDate): String {
        return DateTimeFormatter
            .ofPattern(context.getString(R.string.format_date_title_memory_with_line), Locale.KOREAN)
            .format(writtenDate)
    }

    fun dateToText(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(context.getString(R.string.format_date), Locale.KOREAN).format(date)
    }

    fun dateToTextMonthDay(localDate: LocalDate): String {
        return DateTimeFormatter.ofPattern(context.getString(R.string.format_date_month_day), Locale.KOREAN).format(localDate)
    }

    fun completeWriteText(targetDate: LocalDate): String {
        return context.getString(R.string.format_write_capsule_title, dateToText(targetDate))
    }

    fun getStoreText(text: String, date: LocalDate): CharSequence {
        val dateText = dateToText(date)

        val argText = "$text $dateText"

        val spannableString = SpannableString(context.getString(R.string.format_store_capsule, argText))
        spannableString.setSpan(
            TextAppearanceSpan(context, R.style.B15GreyishBrown),
            text.length + 1,
            argText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableString
    }

    fun getHeaderText(num: Int): CharSequence {
        val numString = num.toString()
        val text = context.getString(R.string.format_home_header, num)

        return SpannableString(text).apply {
            setSpan(TextAppearanceSpan(context, R.style.B16Grape), 0, numString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}

enum class Season(val month: Int, val day: Int) {
    SPRING(1, 4),
    SUMMER(4, 5),
    AUTUMN(7, 7),
    WINTER(10, 7);
}
