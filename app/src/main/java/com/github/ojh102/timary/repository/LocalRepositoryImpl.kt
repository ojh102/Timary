package com.github.ojh102.timary.repository

import android.content.Context
import com.github.ojh102.timary.R
import com.github.ojh102.timary.model.Capsule
import com.github.ojh102.timary.ui.legacy.main.setting.SettingItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class LocalRepositoryImpl @Inject constructor(
    private val context: Context,
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

    override fun getSettingItems(): List<SettingItems> {
        return listOf(
            SettingItems.TitleItem.Term(
                R.string.setting_term,
                context.resources.getString(R.string.setting_term)
            )
        )
    }
}