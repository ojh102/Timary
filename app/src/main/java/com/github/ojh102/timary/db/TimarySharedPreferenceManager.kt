package com.github.ojh102.timary.db

import android.content.SharedPreferences
import javax.inject.Inject

internal class TimarySharedPreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}
