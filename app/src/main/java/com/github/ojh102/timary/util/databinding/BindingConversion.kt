package com.github.ojh102.timary.util.databinding

import android.databinding.BindingConversion
import android.view.View

object BindingConversion {

    @JvmStatic
    @BindingConversion
    fun bindBooleanToVisibility(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE

}