package com.github.ojh102.timary.data.datasource

import android.content.Context
import com.github.ojh102.timary.R
import com.github.ojh102.timary.ui.main.setting.SettingItems
import javax.inject.Inject

internal class SettingDataSourceImpl @Inject constructor(
    private val context: Context
) : SettingDataSource {

    override fun settingItems(): List<SettingItems> {

        return listOf(
            SettingItems.TitleItem.Term(
                R.string.setting_term,
                context.resources.getString(R.string.setting_term)
            )
        )
    }
}
