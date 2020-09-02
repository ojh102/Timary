package com.github.ojh102.timary.data.repository

import android.content.Context
import com.github.ojh102.timary.R
import com.github.ojh102.timary.data.dao.CapsuleDao
import com.github.ojh102.timary.data.entitiy.Capsule
import com.github.ojh102.timary.ui.setting.SettingItems
import com.github.ojh102.timary.ui.store.StoreItems
import com.github.ojh102.timary.util.extension.Season
import com.github.ojh102.timary.util.extension.localDate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

internal class LocalRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val capsuleDao: CapsuleDao
) : LocalRepository {
    override suspend fun getArchivedCapsules(): Flow<List<Capsule>> {
        return capsuleDao.getArchivedCapsules()
    }

    override suspend fun getHomeCapsules(): Flow<List<Capsule>> {
        return capsuleDao.getHomeCapsules()
    }

    override suspend fun getCapsule(id: Long): Flow<Capsule> {
        return capsuleDao.get(id)
    }

    override suspend fun deleteCapsule(id: Long) {
        return capsuleDao.delete(id)
    }

    override suspend fun createOrUpdateCapsule(capsule: Capsule) {
        capsuleDao.createOrUpdate(capsule)
    }

    override fun storeItems(): List<StoreItems> {
        val items = mutableListOf<StoreItems>()

        items.add(StoreItems.DatePicker(context.getString(R.string.store_calendar), LocalDate.MIN))
        items.add(getNextSeason())
        items.add(getLastDayOfYear())
        items.add(getFirstDayOfYear())
        items.add(StoreItems.createRandomItem())

        return items
    }

    override fun settingItems(): List<SettingItems> {
        return listOf(SettingItems.TitleItem.Term)
    }

    private fun getNextSeason(): StoreItems.Event {
        val spring = Season.SPRING.localDate()
        val summer = Season.SUMMER.localDate()
        val autumn = Season.AUTUMN.localDate()
        val winter = Season.WINTER.localDate()

        return when (val targetSeason = listOf(spring, summer, autumn, winter).minOrNull()) {
            spring -> StoreItems.Event(context.getString(R.string.store_spring), targetSeason)
            summer -> StoreItems.Event(context.getString(R.string.store_summer), targetSeason)
            autumn -> StoreItems.Event(context.getString(R.string.store_autumn), targetSeason)
            winter -> StoreItems.Event(context.getString(R.string.store_winter), targetSeason)
            else -> throw IllegalStateException("what the fuck")
        }
    }

    private fun getLastDayOfYear(): StoreItems.Event {
        return StoreItems.Event(
            context.getString(R.string.store_last_day),
            LocalDate.of(
                LocalDate.now().year,
                12,
                31
            )
        )
    }

    private fun getFirstDayOfYear(): StoreItems.Event {
        return StoreItems.Event(
            context.getString(R.string.store_first_day),
            LocalDate.of(
                LocalDate.now().year + 1,
                1,
                1
            )
        )
    }
}
