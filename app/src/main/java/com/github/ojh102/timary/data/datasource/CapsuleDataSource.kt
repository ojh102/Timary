package com.github.ojh102.timary.data.datasource

import com.github.ojh102.timary.data.entitiy.Capsule
import kotlinx.coroutines.flow.Flow

internal interface CapsuleDataSource {
    suspend fun getArchivedCapsules(): Flow<List<Capsule>>
    suspend fun getHomeCapsules(): Flow<List<Capsule>>
    suspend fun get(id: Long): Flow<Capsule>
    suspend fun createOrUpdate(capsule: Capsule)
    suspend fun delete(id: Long)
}
