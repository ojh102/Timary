package com.github.ojh102.timary.ui.main.setting

sealed class SettingItems(val id: Int) {
    sealed class TitleItem(
        id: Int,
        val title: String
    ) : SettingItems(id) {
        class Term(id: Int, title: String) : TitleItem(id, title)
    }
}
