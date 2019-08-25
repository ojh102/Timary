package com.github.ojh102.timary.util.extension

import android.content.Context

fun Context?.hasResource(resId: Int): Boolean {
    return try {
        if (this == null) {
            return false
        }
        this.resources.getResourceName(resId)
        true
    } catch (ignored: Throwable) {
        false
    }
}