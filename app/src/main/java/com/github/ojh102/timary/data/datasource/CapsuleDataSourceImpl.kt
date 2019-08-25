package com.github.ojh102.timary.data.datasource

import com.github.ojh102.timary.data.dao.CapsuleDao
import com.github.ojh102.timary.data.entitiy.Capsule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CapsuleDataSourceImpl @Inject constructor(
    private val capsuleDao: CapsuleDao
) : CapsuleDataSource {

    override suspend fun getArchivedCapsules(): Flow<List<Capsule>> {
        return capsuleDao.getArchivedCapsules()
    }

    override suspend fun getHomeCapsules(): Flow<List<Capsule>> {
        return capsuleDao.getHomeCapsules()
    }

    override suspend fun get(id: Long): Flow<Capsule> {
        return capsuleDao.get(id)
    }

    override suspend fun delete(id: Long) {
        capsuleDao.delete(id)
    }

    override suspend fun createOrUpdate(capsule: Capsule) {
        return capsuleDao.createOrUpdate(capsule)
    }
}