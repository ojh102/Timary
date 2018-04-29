package com.github.ojh102.timary.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.TimaryApplication
import com.github.ojh102.timary.ui.write.store.StoreItem
import com.github.ojh102.timary.util.resources.TimaryResourcesUtil
import java.text.SimpleDateFormat
import java.util.*

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

        val datToHangul = if (month == 0 && day == 1) {
            "첫 날"
        } else if (month == 11 && day == 31) {
            "마지막 날"
        } else {
            dateToTextMonthDay(writtenDate)
        }

        return "${datToHangul}의 기억"
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

        val spannableString = SpannableString(TimaryResourcesUtil.getString(R.string.format_store_capsule, argText))
        spannableString.setSpan(
                TextAppearanceSpan(TimaryApplication.globalApplicationContext, R.style.B15GreyishBrown),
                text.length + 1,
                argText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableString
    }

    private fun getCalendar(date: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = date
        }
    }

    fun getNextSeason(): StoreItem {

        val spring = getNextEventDay(Season.SPRING.month, Season.SPRING.day)
        val summer = getNextEventDay(Season.SUMMER.month, Season.SUMMER.day)
        val autumn = getNextEventDay(Season.AUTUMN.month, Season.AUTUMN.day)
        val winter = getNextEventDay(Season.WINTER.month, Season.WINTER.day)

        val targetSeason = listOf(spring, summer, autumn, winter).min()

        return when (targetSeason) {
            spring -> {
                StoreItem(context.getString(R.string.store_spring), targetSeason)
            }
            summer -> {
                StoreItem(context.getString(R.string.store_summer), targetSeason)
            }
            autumn -> {
                StoreItem(context.getString(R.string.store_autumn), targetSeason)
            }
            winter -> {
                StoreItem(context.getString(R.string.store_winter), targetSeason)
            }
            else -> {
                throw IllegalStateException("what the fuck")
            }
        }
    }

    fun getLastDayOfYear(): StoreItem {
        return StoreItem(
                context.getString(R.string.store_last_day),
                Calendar.getInstance().apply { set(get(Calendar.YEAR), 11, 31) }.timeInMillis
        )
    }

    fun getFirstDayOfYear(): StoreItem {
        return StoreItem(
                context.getString(R.string.store_first_day),
                Calendar.getInstance().apply { set(get(Calendar.YEAR) + 1, 0, 1) }.timeInMillis
        )
    }

    fun getRandomDay(): StoreItem {
        val curCal = Calendar.getInstance()

        val year = curCal.get(Calendar.YEAR)

        val random = Random().apply { setSeed(System.currentTimeMillis()) }

        val ranMonth = random.nextInt(12)
        val ranDay = random.nextInt(29)

        val targetCal = Calendar.getInstance()
        targetCal.set(year, ranMonth, ranDay)
        targetCal.before(curCal)
        targetCal.set(year + 1, ranMonth, ranDay)

        return StoreItem(context.getString(R.string.store_random), targetCal.timeInMillis)
    }

    private fun getNextEventDay(targetMonth: Int, targetDay: Int): Long {
        return Calendar.getInstance().apply {

            val curYear = get(Calendar.YEAR)
            val curMon = get(Calendar.MONTH)
            val curDay = get(Calendar.DAY_OF_MONTH)

            val targetYear = if (curMon >= targetMonth && curDay >= targetDay) {
                curYear + 1
            } else {
                curYear
            }

            set(targetYear, targetMonth, targetDay)
        }.timeInMillis
    }
}

enum class Season(val month: Int, val day: Int) {
    SPRING(1, 4),
    SUMMER(4, 5),
    AUTUMN(7, 7),
    WINTER(10, 7);
}