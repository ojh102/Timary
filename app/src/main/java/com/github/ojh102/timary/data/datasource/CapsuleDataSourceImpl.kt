package com.github.ojh102.timary.data.datasource

import com.github.ojh102.timary.data.dao.CapsuleDao
import com.github.ojh102.timary.data.entitiy.Capsule
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CapsuleDataSourceImpl @Inject constructor(
    private val capsuleDao: CapsuleDao
) : CapsuleDataSource {

    override suspend fun gets(): Flow<List<Capsule>> {
        return capsuleDao.gets()
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