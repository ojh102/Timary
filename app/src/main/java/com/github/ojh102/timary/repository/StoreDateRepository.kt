package com.github.ojh102.timary.repository

import android.content.Context
import com.github.ojh102.timary.R
import com.github.ojh102.timary.ui.write.store.StoreItem
import com.github.ojh102.timary.util.TimaryParser
import io.reactivex.Observable
import javax.inject.Inject

class StoreDateRepository @Inject constructor(
        private val context: Context,
        private val timaryParser: TimaryParser
) {

    fun getStoreDateList(): Observable<List<StoreItem>> {
        val items = mutableListOf<StoreItem>()

        items.add(StoreItem(context.getString(R.string.store_calendar), 0L))
        items.add(timaryParser.getNextSeason())
        items.add(timaryParser.getLastDayOfYear())
        items.add(timaryParser.getFirstDayOfYear())
        items.add(timaryParser.getRandomDay())

        return Observable.just(items)
    }
}