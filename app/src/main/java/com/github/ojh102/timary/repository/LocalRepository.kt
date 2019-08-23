package com.github.ojh102.timary.repository

import com.github.ojh102.timary.model.Capsule

internal interface LocalRepository {
    suspend fun getCapsules(): List<Capsule>
    suspend fun getCapsule(id: Long): Capsule
    suspend fun createOrUpdateCapsule(capsule: Capsule)
}