package com.github.ojh102.timary.repository

import com.github.ojh102.timary.model.Capsule
import javax.inject.Inject

internal class LocalRepositoryImpl @Inject constructor(
    private val capsuleDataSource: CapsuleDataSource
) : LocalRepository {
    override suspend fun getCapsules(): List<Capsule> {
        return capsuleDataSource.gets()
    }

    override suspend fun getCapsule(id: Long): Capsule {
        return capsuleDataSource.get(id)
    }

    override suspend fun createOrUpdateCapsule(capsule: Capsule) {
        capsuleDataSource.createOrUpdate(capsule)
    }
}