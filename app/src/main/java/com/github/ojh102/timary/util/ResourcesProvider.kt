package com.github.ojh102.timary.util

internal interface ResourcesProvider {
    fun getString(resId: Int): String
}
