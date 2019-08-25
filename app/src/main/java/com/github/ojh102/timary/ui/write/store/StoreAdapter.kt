package com.github.ojh102.timary.ui.write.store

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.ojh102.timary.databinding.ViewStoreBinding
import com.github.ojh102.timary.util.extension.inflater
import org.threeten.bp.LocalDate
import java.util.Random
import javax.inject.Inject

internal class StoreAdapter @Inject constructor() : ListAdapter<StoreItem, StoreViewHolder>(object : DiffUtil.ItemCallback<StoreItem>() {

    override fun areItemsTheSame(oldItem: StoreItem, newItem: StoreItem): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: StoreItem, newItem: StoreItem): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}) {

    interface Callbacks {
        fun onItemSelect(item: StoreItem, position: Int)
    }

    var callbacks: Callbacks? = null

    lateinit var items: MutableList<StoreItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ViewStoreBinding.inflate(parent.inflater(), parent, false)

        binding.itemSelectedListener = object : StoreViewHolder.OnItemSelectedListener {
            override fun onItemSelect(item: StoreItem, position: Int) {
                items.forEach {
                    it.setSelected(false)
                }

                if (position == items.size - 1) {
                    items[position].date = createRandomDate()
                }

                items[position].setSelected(true)

                callbacks?.onItemSelect(item, position)

                notifyDataSetChanged()
            }
        }

        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<StoreItem>?) {
        super.submitList(list)

        list?.let {
            items = it.toMutableList()
        }
    }

    private fun createRandomDate(): LocalDate {
        val now = LocalDate.now()

        val year = now.year

        val random = Random().apply { setSeed(System.currentTimeMillis()) }

        val ranMonth = random.nextInt(12)
        val ranDay = random.nextInt(29)

        return LocalDate.of(
            year + 1,
            ranMonth + 1,
            ranDay + 1
        )
    }
}
