package com.github.ojh102.timary.data.repository

import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.ui.main.setting.SettingItems
import com.github.ojh102.timary.ui.write.store.StoreItems
import kotlinx.coroutines.flow.Flow

internal interface LocalRepository {
    suspend fun getArchivedCapsules(): Flow<List<Capsule>>
    suspend fun getHomeCapsules(): Flow<List<Capsule>>
    suspend fun getCapsule(id: Long): Flow<Capsule>
    suspend fun deleteCapsule(id: Long)
    suspend fun createOrUpdateCapsule(capsule: Capsule)

    fun storeItems(): List<StoreItems>
    fun settingItems(): List<SettingItems>
}
