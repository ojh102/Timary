package com.github.ojh102.timary.util

import android.content.Context

internal interface ResourcesProvider {
    fun getContext(): Context
    fun getString(resId: Int): String
}
