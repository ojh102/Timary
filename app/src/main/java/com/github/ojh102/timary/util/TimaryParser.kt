package com.github.ojh102.timary.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimaryParser(private val context: Context) {

    fun dateToYear(date: Long): String {
        if (date == 0L) {
            return ""
        }

        val calendar = getCalendar(date)
        val year = calendar[Calendar.YEAR]

        return context.getString(R.string.format_year, year)
    }

    fun dateToCapsuleTitle(writtenDate: Long): String {
        if (writtenDate == 0L) {
            return ""
        }

        val calendar = getCalendar(writtenDate)

        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datText = if (month == 0 && day == 1) {
            context.getString(R.string.capsule_first_day)
        } else if (month == 11 && day == 31) {
            context.getString(R.string.capsule_last_day)
        } else {
            dateToTextMonthDay(writtenDate)
        }

        return context.getString(R.string.format_capsule_title_format, datText)
    }

    fun dDay(targetDate: Long): Float {
        val diff = targetDate - System.currentTimeMillis()
        val diffDay = (diff.toFloat() / (24 * 60 * 60 * 1000))

        return diffDay
    }

    fun dateToCapsuleDday(targetDate: Long): String {
        if (targetDate == 0L) {
            return ""
        }

        val diffDay = dDay(targetDate)

        val dDay = when {
            diffDay <= 0 -> context.getString(R.string.today)
            (diffDay < 1 && diffDay > 0) -> "1"
            else -> diffDay.toInt().toString()
        }

        return context.getString(R.string.format_dday, getTextFromEventDay(targetDate), dDay)
    }

    fun dateToArchiveDay(targetDate: Long): String {
        if (targetDate == 0L) {
            return ""
        }

        return context.getString(R.string.format_dday_archive, getTextFromEventDay(targetDate))
    }

    fun getTextFromEventDay(targetDate: Long): String {
        val cal = Calendar.getInstance().apply {
            timeInMillis = targetDate
        }

        val targetMonth = cal.get(Calendar.MONTH)
        val targetDay = cal.get(Calendar.DAY_OF_MONTH)

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

    fun dateToTitleWithLine(writtenDate: Long): String {
        if (writtenDate == 0L) {
            return ""
        }

        val calendar = getCalendar(writtenDate)
        val sdf = SimpleDateFormat(context.getString(R.string.format_date_title_memory_with_line), Locale.KOREAN)
        return sdf.format(calendar.time)
    }

    fun dateToText(date: Long): String {
        if (date == 0L) {
            return ""
        }

        val calendar = getCalendar(date)
        val sdf = SimpleDateFormat(context.getString(R.string.format_date), Locale.KOREAN)
        return sdf.format(calendar.time)
    }

    fun dateToTextMonthDay(date: Long): String {
        if (date == 0L) {
            return ""
        }

        return SimpleDateFormat(
                context.getString(R.string.format_date_month_day), Locale.KOREAN
        ).format(getCalendar(date).time)
    }

    fun completeWriteText(targetDate: Long): String {
        if (targetDate == 0L) {
            return ""
        }

        return context.getString(R.string.format_write_capsule_title, dateToText(targetDate))
    }

    fun getStoreText(text: String, date: Long): CharSequence {
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

    private fun getCalendar(date: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = date
        }
    }
}

enum class Season(val month: Int, val day: Int) {
    SPRING(1, 4),
    SUMMER(4, 5),
    AUTUMN(7, 7),
    WINTER(10, 7);
}
