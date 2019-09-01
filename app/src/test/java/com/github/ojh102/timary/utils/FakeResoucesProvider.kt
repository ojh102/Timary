package com.github.ojh102.timary.utils

import com.github.ojh102.timary.util.ResourcesProvider

internal class FakeResoucesProvider : ResourcesProvider {
    override fun getString(resId: Int): String {
        return ""
    }
}
