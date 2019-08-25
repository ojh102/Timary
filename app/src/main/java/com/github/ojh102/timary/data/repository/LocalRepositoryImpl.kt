package com.github.ojh102.timary.data.repository

import com.github.ojh102.timary.data.datasource.CapsuleDataSource
import com.github.ojh102.timary.data.datasource.SettingDataSource
import com.github.ojh102.timary.data.datasource.StoreDateDataSource
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.ui.main.setting.SettingItems
import com.github.ojh102.timary.ui.write.store.StoreItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LocalRepositoryImpl @Inject constructor(
    private val capsuleDataSource: CapsuleDataSource,
    private val storeDateDataSource: StoreDateDataSource,
    private val settingDataSource: SettingDataSource
) : LocalRepository {
    override suspend fun getCapsules(): Flow<List<Capsule>> {
        return capsuleDataSource.gets()
    }

    override suspend fun getCapsule(id: Long): Flow<Capsule> {
        return capsuleDataSource.get(id)
    }

    override suspend fun deleteCapsule(id: Long) {
        return capsuleDataSource.delete(id)
    }

    override suspend fun createOrUpdateCapsule(capsule: Capsule) {
        capsuleDataSource.createOrUpdate(capsule)
    }

    override fun storeItems(): List<StoreItem> {
        return storeDateDataSource.storeItems()
    }

    override fun settingItems(): List<SettingItems> {
        return settingDataSource.settingItems()
    }
}