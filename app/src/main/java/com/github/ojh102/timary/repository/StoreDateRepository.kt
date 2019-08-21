package com.github.ojh102.timary.repository

import android.content.Context
import com.github.ojh102.timary.R
import com.github.ojh102.timary.ui.legacy.write.store.StoreItem
import com.github.ojh102.timary.util.Season
import io.reactivex.Single
import java.util.Calendar
import java.util.Random
import javax.inject.Inject

internal class StoreDateRepository @Inject constructor(
    private val context: Context
) {

    fun getStoreDateList(): Single<List<StoreItem>> {
        val items = mutableListOf<StoreItem>()

        items.add(StoreItem(context.getString(R.string.store_calendar), 0L))
        items.add(getNextSeason())
        items.add(getLastDayOfYear())
        items.add(getFirstDayOfYear())
        items.add(getRandomDay())

        return Single.just(items)
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
                Calendar.getInstance().apply { set(get(Calendar.YEAR), 11, 31) }.timeInMillis
        )
    }

    private fun getFirstDayOfYear(): StoreItem {
        return StoreItem(
                context.getString(R.string.store_first_day),
                Calendar.getInstance().apply { set(get(Calendar.YEAR) + 1, 0, 1) }.timeInMillis
        )
    }

    private fun getRandomDay(): StoreItem {
        val curCal = Calendar.getInstance()

        val year = curCal.get(Calendar.YEAR)

        val random = Random().apply { setSeed(System.currentTimeMillis()) }

        val ranMonth = random.nextInt(12)
        val ranDay = random.nextInt(29)

        val targetCal = Calendar.getInstance()
        targetCal.set(year, ranMonth, ranDay)
        targetCal.before(curCal)
        targetCal.set(year + 1, ranMonth, ranDay)

        return StoreItem(context.getString(R.string.store_random), targetCal.timeInMillis)
    }

    private fun getNextEventDay(targetMonth: Int, targetDay: Int): Long {
        return Calendar.getInstance().apply {

            val curYear = get(Calendar.YEAR)
            val curMon = get(Calendar.MONTH)
            val curDay = get(Calendar.DAY_OF_MONTH)

            val targetYear = if (curMon >= targetMonth && curDay >= targetDay) {
                curYear + 1
            } else {
                curYear
            }

            set(targetYear, targetMonth, targetDay)
        }.timeInMillis
    }
}
