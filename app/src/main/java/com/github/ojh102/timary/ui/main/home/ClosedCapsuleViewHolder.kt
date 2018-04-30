package com.github.ojh102.timary.ui.main.home

import android.view.View
import com.github.ojh102.timary.R
import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewCapsuleCloseBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.TimaryParser
import com.github.ojh102.timary.util.extension.toast

class ClosedCapsuleViewHolder(
        private val binding: ViewCapsuleCloseBinding,
        timaryParser: TimaryParser
) : BaseViewHolder<Capsule>(binding) {

    private val context by lazy(LazyThreadSafetyMode.NONE) {
        binding.root.context
    }

    init {
        binding.timaryParser = timaryParser

        binding.capsuleClickListener = View.OnClickListener {
            binding.capsule?.let {
                val diffDay = it.dDay()
                val dDay = if(diffDay < 1) {
                    1
                } else {
                    diffDay.toInt()
                }

                context.toast(context.getString(R.string.format_click_capsule_close, dDay))
            }
        }
    }

    override fun bind(item: Capsule) {
        binding.capsule = item
    }

}