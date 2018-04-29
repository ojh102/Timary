package com.github.ojh102.timary.ui.main.setting

import android.view.View
import android.widget.CompoundButton

data class SwitchItem(
        val title: String,
        val description: String,
        var isChecked: Boolean = true,
        val onCheckedChangedListener: CompoundButton.OnCheckedChangeListener
): SettingItem()

data class TitleItem(
        val title: String,
        val onClickListener: View.OnClickListener
): SettingItem()

class LineItem: SettingItem()

class DeepLineItem: SettingItem()

sealed class SettingItem