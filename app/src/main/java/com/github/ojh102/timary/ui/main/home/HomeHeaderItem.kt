package com.github.ojh102.timary.ui.main.home

import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.TimaryApplication
import com.github.ojh102.timary.util.resources.TimaryResourcesUtil

data class HomeHeaderItem(
        private val num: Int
) {

    fun getHeaderText(): CharSequence {
        val context = TimaryApplication.globalApplicationContext

        val numString = num.toString()

        val text = TimaryResourcesUtil.getString(R.string.format_home_header, numString)

        return SpannableString(text).apply {
            setSpan(TextAppearanceSpan(context, R.style.B16Grape), 0, numString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

}