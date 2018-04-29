package com.github.ojh102.timary.util.resources

import android.graphics.Color
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.github.ojh102.timary.TimaryApplication

object TimaryResourcesUtil {

    private val context by lazy(LazyThreadSafetyMode.NONE) {
        TimaryApplication.globalApplicationContext
    }

    fun hasResource(resId: Int): Boolean {
        return try {
            context.resources.getResourceName(resId)
            true
        } catch (ignored: Throwable) {
            false
        }
    }

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg arg: Any): String {
        return context.getString(resId, *arg)
    }

    fun dpToPixel(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun getColor(@ColorRes resId: Int): Int {
        return if (resId == 0) Color.TRANSPARENT else ContextCompat.getColor(context, resId)
    }

}