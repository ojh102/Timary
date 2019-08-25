package com.github.ojh102.timary.data

import com.github.ojh102.timary.base.BaseItem

internal open class SelectableItem(override val itemId: String, open var isSelected: Boolean = false) : BaseItem(itemId)
