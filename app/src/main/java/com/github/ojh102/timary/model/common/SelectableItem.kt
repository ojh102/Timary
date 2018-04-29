package com.github.ojh102.timary.model.common

open class SelectableItem {

    private var isSelected: Boolean = false

    fun isSelected(): Boolean {
        return isSelected
    }

    fun setSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }
}
