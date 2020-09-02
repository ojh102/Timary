package com.github.ojh102.timary.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class ResourcesProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourcesProvider {

    override fun getContext(): Context {
        return context
    }

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
