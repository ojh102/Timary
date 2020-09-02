package com.github.ojh102.timary.data.datasource

import com.github.ojh102.timary.ui.store.StoreItems

internal interface StoreDateDataSource {
    fun storeItems(): List<StoreItems>
}
