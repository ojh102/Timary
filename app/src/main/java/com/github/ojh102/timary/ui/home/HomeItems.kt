package com.github.ojh102.timary.ui.home

import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseItem
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.util.ResourcesUtil

internal sealed class HomeItems(val id: Long) : BaseItem(id.toString()) {
    data class Header(val storedCapsuleSize: Int) : HomeItems(storedCapsuleSize.toLong()) {
        fun headerText(): CharSequence {
            val text = String.format(ResourcesUtil.getString(R.string.format_home_header), storedCapsuleSize)

            return SpannableString(text).apply {
                setSpan(
                    TextAppearanceSpan(ResourcesUtil.getContext(), R.style.B16Grape),
                    0,
                    storedCapsuleSize.toString().length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    sealed class StoredCapsule(open val capsule: Capsule) : HomeItems(capsule.id) {
        data class ClosedCapsule(override val capsule: Capsule) : StoredCapsule(capsule)

        data class OpenedCapsule(override val capsule: Capsule) : StoredCapsule(capsule)
    }
}
