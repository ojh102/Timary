package com.github.ojh102.timary.ui.main.archieve

import com.github.ojh102.timary.base.BaseViewHolder
import com.github.ojh102.timary.databinding.ViewCapsuleArchiveBinding
import com.github.ojh102.timary.model.realm.Capsule
import com.github.ojh102.timary.util.TimaryParser

class ArchiveCapsuleViewHolder(
        private val binding: ViewCapsuleArchiveBinding,
        timaryParser: TimaryParser
) : BaseViewHolder<Capsule>(binding) {

    init {
        binding.timaryParser = timaryParser
    }

    override fun bind(item: Capsule) {
        binding.capsule = item
    }

}