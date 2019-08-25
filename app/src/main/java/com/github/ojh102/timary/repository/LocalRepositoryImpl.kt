package com.github.ojh102.timary.repository

import com.github.ojh102.timary.model.Capsule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LocalRepositoryImpl @Inject constructor(
    private val capsuleDataSource: CapsuleDataSource
) : LocalRepository {
    override suspend fun getCapsules(): Flow<List<Capsule>> {
        return capsuleDataSource.gets()
    }

    override suspend fun getCapsule(id: Long): Flow<Capsule> {
        return capsuleDataSource.get(id)
    }

    override suspend fun createOrUpdateCapsule(capsule: Capsule) {
        capsuleDataSource.createOrUpdate(capsule)
    }
}