package com.github.ojh102.timary.ui.main.home

import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewCapsuleOpenBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.TimaryParser

class OpenedCapsuleViewHolder(
        private val binding: ViewCapsuleOpenBinding,
        timaryParser: TimaryParser
) : BaseViewHolder<Capsule>(binding) {

    init {
        binding.timaryParser = timaryParser
    }

    override fun bind(item: Capsule) {
        binding.capsule = item
    }
}