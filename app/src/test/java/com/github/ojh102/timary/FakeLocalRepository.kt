package com.github.ojh102.timary

import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.data.repository.LocalRepository
import com.github.ojh102.timary.ui.main.setting.SettingItems
import com.github.ojh102.timary.ui.write.store.StoreItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

internal class FakeLocalRepository : LocalRepository {
    override suspend fun getArchivedCapsules(): Flow<List<Capsule>> {
        return listOf(listOf<Capsule>()).asFlow()
    }

    override suspend fun getHomeCapsules(): Flow<List<Capsule>> {
        return listOf(listOf<Capsule>()).asFlow()
    }

    override suspend fun getCapsule(id: Long): Flow<Capsule> {
        return listOf<Capsule>().asFlow()
    }

    override suspend fun deleteCapsule(id: Long) {

    }

    override suspend fun createOrUpdateCapsule(capsule: Capsule) {

    }

    override fun storeItems(): List<StoreItems> {
        return emptyList()
    }

    override fun settingItems(): List<SettingItems> {
        return emptyList()
    }
}
