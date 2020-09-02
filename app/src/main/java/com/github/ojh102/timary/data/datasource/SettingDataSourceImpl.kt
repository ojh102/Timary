package com.github.ojh102.timary.data.datasource

import com.github.ojh102.timary.ui.setting.SettingItems
import javax.inject.Inject

internal class SettingDataSourceImpl @Inject constructor() : SettingDataSource {

    override fun settingItems(): List<SettingItems> {
        return listOf(SettingItems.TitleItem.Term)
    }
}
