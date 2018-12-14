package com.github.ojh102.timary.ui.main.setting

import com.github.ojh102.timary.base.BaseViewModel
import com.github.ojh102.timary.repository.SettingRepository
import io.reactivex.Single
import javax.inject.Inject

interface SettingContract {

    interface Inputs

    interface Outputs {
        fun settingItemList(): Single<List<SettingItems>>
    }

    class SettingViewModel @Inject constructor(
            private val settingRepository: SettingRepository
    ) : BaseViewModel(), Inputs, Outputs {

        val inputs: Inputs = this
        val outputs: Outputs = this

        override fun settingItemList(): Single<List<SettingItems>> {
            return settingRepository.getSettingItemList()
        }
    }

}