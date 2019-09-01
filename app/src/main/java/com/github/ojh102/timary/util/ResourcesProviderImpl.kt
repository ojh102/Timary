package com.github.ojh102.timary.util

import android.content.Context
import javax.inject.Inject

internal class ResourcesProviderImpl @Inject constructor(
    private val context: Context
): ResourcesProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
