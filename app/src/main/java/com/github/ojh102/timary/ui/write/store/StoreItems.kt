package com.github.ojh102.timary.ui.write.store

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.data.SelectableItem
import com.github.ojh102.timary.util.ResourcesUtil
import com.github.ojh102.timary.util.extension.yyMMdd
import java.util.Random
import org.threeten.bp.LocalDate

internal sealed class StoreItems(
    open val text: String,
    open var date: LocalDate
) : SelectableItem(text) {

    data class DatePicker(override val text: String, override var date: LocalDate) : StoreItems(text, date)

    data class Event(override val text: String, override var date: LocalDate) : StoreItems(text, date)

    data class Random(override val text: String, override var date: LocalDate) : StoreItems(text, date)

    fun storeText(): CharSequence {
        val dateText = date.yyMMdd()

        val argText = "$text $dateText"

        val spannableString = SpannableString(String.format(ResourcesUtil.getString(R.string.format_store_capsule), argText))

        spannableString.setSpan(
            TextAppearanceSpan(ResourcesUtil.getContext(), R.style.B15GreyishBrown),
            text.length + 1,
            argText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableString
    }

    companion object {
        fun createRandomItem(): Random {
            val random = Random().apply { setSeed(System.currentTimeMillis()) }

            val ranMonth = random.nextInt(12)
            val ranDay = random.nextInt(7)

            val targetDate = LocalDate.of(
                LocalDate.now().year + 1,
                ranMonth + 1,
                ranDay + 1
            )

            return Random(ResourcesUtil.getString(R.string.store_random), targetDate)
        }
    }
}
