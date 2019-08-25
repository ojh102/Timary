package com.github.ojh102.timary.repository

import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.ui.legacy.main.setting.SettingItems
import kotlinx.coroutines.flow.Flow

internal interface LocalRepository {
    suspend fun getCapsules(): Flow<List<Capsule>>
    suspend fun getCapsule(id: Long): Flow<Capsule>
    suspend fun deleteCapsule(id: Long)
    suspend fun createOrUpdateCapsule(capsule: Capsule)

    fun getSettingItems(): List<SettingItems>
}