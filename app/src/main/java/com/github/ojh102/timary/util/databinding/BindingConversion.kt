package com.github.ojh102.timary.util.databinding

import android.view.View
import androidx.databinding.BindingConversion

object BindingConversion {

    @JvmStatic
    @BindingConversion
    fun bindBooleanToVisibility(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE
}
