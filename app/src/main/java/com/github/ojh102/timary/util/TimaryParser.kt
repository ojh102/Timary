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
    fun dateToTitleWithLine(writtenDate: LocalDate): String {
        return DateTimeFormatter
            .ofPattern(context.getString(R.string.format_date_title_memory_with_line), Locale.KOREAN)
            .format(writtenDate)
    }

    fun dateToText(date: LocalDate): String {
        return DateTimeFormatter.ofPattern(context.getString(R.string.format_date), Locale.KOREAN).format(date)
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
}

enum class Season(val month: Int, val day: Int) {
    SPRING(1, 4),
    SUMMER(4, 5),
    AUTUMN(7, 7),
    WINTER(10, 7);
}
