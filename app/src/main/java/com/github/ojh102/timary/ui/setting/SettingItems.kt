package com.github.ojh102.timary.ui.setting

import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseItem
import com.github.ojh102.timary.util.ResourcesUtil

internal sealed class SettingItems : BaseItem {
    sealed class TitleItem(val title: String, override val itemId: String = title) : SettingItems() {
        object Term : TitleItem(ResourcesUtil.getString(R.string.setting_term))
    }
}
