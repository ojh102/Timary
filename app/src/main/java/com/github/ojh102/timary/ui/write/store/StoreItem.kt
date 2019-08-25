package com.github.ojh102.timary.ui.write.store

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.data.SelectableItem
import com.github.ojh102.timary.util.ResourcesUtil
import com.github.ojh102.timary.util.extension.yyMMdd
import org.threeten.bp.LocalDate

data class StoreItem(
    val text: String,
    var date: LocalDate
) : SelectableItem() {
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
}
