package com.github.ojh102.timary.ui.archive

import com.github.ojh102.timary.base.BaseItem
import com.github.ojh102.timary.data.entitiy.Capsule

internal sealed class ArchiveItems(open val capsule: Capsule, override val itemId: String = capsule.id.toString()) : BaseItem {
    data class ArchiveItem(override val capsule: Capsule) : ArchiveItems(capsule)
}
