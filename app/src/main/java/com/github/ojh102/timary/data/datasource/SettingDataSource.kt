package com.github.ojh102.timary.data.datasource

import com.github.ojh102.timary.ui.setting.SettingItems

internal interface SettingDataSource {
    fun settingItems(): List<SettingItems>
}
