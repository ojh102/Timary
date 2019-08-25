package com.github.ojh102.timary.ui.main.home

import com.github.ojh102.timary.data.entitiy.Capsule


internal sealed class HomeItems(val id: Long) {
    data class Header(val storedCapsuleSize: Int) : HomeItems(storedCapsuleSize.toLong())

    sealed class StoredCapsule(open val capsule: Capsule) : HomeItems(capsule.id) {
        data class ClosedCapsule(override val capsule: Capsule) : StoredCapsule(capsule)

        data class OpenedCapsule(override val capsule: Capsule) : StoredCapsule(capsule)
    }
}
