package com.github.ojh102.timary.ui.write.store

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseRecyclerViewAdapter
import com.github.ojh102.timary.databinding.ViewStoreBinding
import com.github.ojh102.timary.util.extension.inflater
import java.util.*

class StoreAdapter(
        private val context: Context
) : BaseRecyclerViewAdapter() {

    interface Callbacks {
        fun onItemSelect(item: StoreItem, position: Int)
    }

    var callbacks : Callbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ViewStoreBinding.inflate(parent.inflater(), parent, false)

        binding.itemSelectedListener = object : StoreViewHolder.OnItemSelectedListener {
            override fun onItemSelect(item: StoreItem, position: Int) {
                items.map { it as StoreItem }.forEach {
                    it.setSelected(false)
                }

                if(position == items.size - 1) {
                    items.removeAt(position)

                    items.add(createRandomStoreItem())
                }

                (items[position] as StoreItem).setSelected(true)

                callbacks?.onItemSelect(item, position)

                notifyDataSetChanged()
            }
        }

        return StoreViewHolder(binding)
    }

    private fun createRandomStoreItem(): StoreItem {
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

}