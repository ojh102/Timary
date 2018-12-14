package com.github.ojh102.timary.ui.main.setting

import android.view.View
import android.widget.CompoundButton


sealed class SettingItems(val id: Int) {
    class SwitchItem(
            id: Int,
            val title: String,
            val description: String,
            var isChecked: Boolean = true,
            val onCheckedChangedListener: CompoundButton.OnCheckedChangeListener
    ) : SettingItems(id)

    class TitleItem(
            id: Int,
            val title: String,
            val onClickListener: View.OnClickListener
    ) : SettingItems(id)

    class LineItem(id: Int) : SettingItems(id)

    class DeepLineItem(id: Int) : SettingItems(id)
}