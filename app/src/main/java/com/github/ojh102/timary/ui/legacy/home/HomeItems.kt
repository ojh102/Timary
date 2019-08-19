package com.github.ojh102.timary.ui.legacy.home

import com.github.ojh102.timary.model.realm.Capsule

sealed class HomeItems(val id: Long) {
    data class Header(val storedCapsuleSize: Int) : HomeItems(storedCapsuleSize.toLong())

    sealed class StoredCapsule(val capsule: Capsule) : HomeItems(capsule.id) {
        class ClosedCapsule(capsule: Capsule) : StoredCapsule(capsule)

        class OpenedCapsule(capsule: Capsule) : StoredCapsule(capsule)
    }
}
