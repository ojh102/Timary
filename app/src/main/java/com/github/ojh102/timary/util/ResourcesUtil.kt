package com.github.ojh102.timary.util

import android.app.Application
import android.content.Context
import android.content.res.Resources

internal object ResourcesUtil {
    private lateinit var context: Application
    private lateinit var resources: Resources

    fun initialize(application: Application) {
        context = application
        resources = context.resources
    }

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    fun getContext(): Context {
        return context.applicationContext
    }
}
