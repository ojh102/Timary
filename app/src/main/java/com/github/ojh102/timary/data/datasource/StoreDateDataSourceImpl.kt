package com.github.ojh102.timary.data.datasource

import android.content.Context
import com.github.ojh102.timary.R
import com.github.ojh102.timary.ui.write.store.StoreItem
import com.github.ojh102.timary.util.Season
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.util.Calendar
import java.util.Random
import javax.inject.Inject

internal class StoreDateDataSourceImpl @Inject constructor(
    private val context: Context

) : StoreDateDataSource {

    override fun storeItems(): List<StoreItem> {
        val items = mutableListOf<StoreItem>()

        items.add(StoreItem(context.getString(R.string.store_calendar), LocalDate.MIN))
        items.add(getNextSeason())
        items.add(getLastDayOfYear())
        items.add(getFirstDayOfYear())
        items.add(getRandomDay())

        return items
    }

    private fun getNextSeason(): StoreItem {

        val spring = getNextEventDay(Season.SPRING.month, Season.SPRING.day)
        val summer = getNextEventDay(Season.SUMMER.month, Season.SUMMER.day)
        val autumn = getNextEventDay(Season.AUTUMN.month, Season.AUTUMN.day)
        val winter = getNextEventDay(Season.WINTER.month, Season.WINTER.day)

        val targetSeason = listOf(spring, summer, autumn, winter).min()

        return when (targetSeason) {
            spring -> {
                StoreItem(context.getString(R.string.store_spring), targetSeason)
            }
            summer -> {
                StoreItem(context.getString(R.string.store_summer), targetSeason)
            }
            autumn -> {
                StoreItem(context.getString(R.string.store_autumn), targetSeason)
            }
            winter -> {
                StoreItem(context.getString(R.string.store_winter), targetSeason)
            }
            else -> {
                throw IllegalStateException("what the fuck")
            }
        }
    }

    private fun getLastDayOfYear(): StoreItem {
        return StoreItem(
            context.getString(R.string.store_last_day),
            LocalDate.of(
                LocalDate.now().year,
                12,
                31
            )
        )
    }

    private fun getFirstDayOfYear(): StoreItem {
        return StoreItem(
            context.getString(R.string.store_first_day),
            LocalDate.of(
                LocalDate.now().year + 1,
                1,
                1
            )
        )
    }

    private fun getRandomDay(): StoreItem {
        val random = Random().apply { setSeed(System.currentTimeMillis()) }

        val ranMonth = random.nextInt(12)
        val ranDay = random.nextInt(29)

        val targetDate = LocalDate.of(
            LocalDate.now().year + 1,
            ranMonth + 1,
            ranDay
        )

        return StoreItem(context.getString(R.string.store_random), targetDate)
    }

    private fun getNextEventDay(targetMonth: Int, targetDay: Int): LocalDate {
        val now = LocalDate.now()

        val targetYear = if (now.monthValue >= targetMonth && now.dayOfMonth >= targetDay) {
            now.year + 1
        } else {
            now.year
        }

        return LocalDate.of(targetYear, targetMonth + 1, targetDay)
    }
}
