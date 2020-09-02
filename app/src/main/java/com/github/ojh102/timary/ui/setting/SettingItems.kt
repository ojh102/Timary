package com.github.ojh102.timary.ui.setting

import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseItem
import com.github.ojh102.timary.util.ResourcesUtil

internal sealed class SettingItems(val id: String) : BaseItem(id) {
    sealed class TitleItem(val title: String) : SettingItems(title) {
        object Term : TitleItem(ResourcesUtil.getString(R.string.setting_term))
    }
}
