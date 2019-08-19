package com.github.ojh102.timary.ui.legacy.setting

sealed class SettingItems(val id: Int) {
    sealed class SwitchItem(
        id: Int,
        val title: String,
        val description: String,
        var isChecked: Boolean = true
    ) : SettingItems(id) {
        class Alert(id: Int, title: String, description: String, isChecked: Boolean) : SwitchItem(id, title, description, isChecked)
    }

    sealed class TitleItem(
        id: Int,
        val title: String
    ) : SettingItems(id) {
        class Term(id: Int, title: String) : TitleItem(id, title)
    }

    class LineItem(id: Int) : SettingItems(id)

    class DeepLineItem(id: Int) : SettingItems(id)
}
