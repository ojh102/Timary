package com.github.ojh102.timary.ui.main.archive

import com.github.ojh102.timary.base.BaseItem
import com.github.ojh102.timary.data.entitiy.Capsule

internal sealed class ArchiveItems(open val capsule: Capsule) : BaseItem(capsule.id.toString()) {
    data class ArchiveItem(override val capsule: Capsule) : ArchiveItems(capsule)
}
