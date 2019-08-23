package com.github.ojh102.timary.repository

import com.github.ojh102.timary.model.Capsule

internal interface CapsuleDataSource {
    suspend fun gets(): List<Capsule>
    suspend fun get(id: Long): Capsule
    suspend fun createOrUpdate(capsule: Capsule)
}