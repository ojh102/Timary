package com.github.ojh102.timary.ui.write.store

import com.github.ojh102.timary.data.SelectableItem
import org.threeten.bp.LocalDate

data class StoreItem(
    val text: String,
    var date: LocalDate
) : SelectableItem()
