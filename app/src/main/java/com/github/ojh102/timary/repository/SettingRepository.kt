package com.github.ojh102.timary.repository

import android.content.Context
import android.view.View
import android.widget.CompoundButton
import com.github.ojh102.timary.R
import com.github.ojh102.timary.db.TimarySharedPreferenceManager
import com.github.ojh102.timary.ui.main.setting.SettingItems
import com.github.ojh102.timary.util.KEY_SETTING_NOTIFICATION
import com.github.ojh102.timary.util.Navigator
import io.reactivex.Single
import javax.inject.Inject

class SettingRepository @Inject constructor(
        private val context: Context,
        private val timarySharedPreferenceManager: TimarySharedPreferenceManager
) {

    fun getSettingItemList(): Single<List<SettingItems>> {

        val items = mutableListOf<SettingItems>().apply {
            add(SettingItems.SwitchItem(
                    R.string.setting_notification,
                    context.getString(R.string.setting_notification),
                    context.getString(R.string.setting_notification_sub),
                    timarySharedPreferenceManager.getBoolean(KEY_SETTING_NOTIFICATION, true),
                    CompoundButton.OnCheckedChangeListener { _, isChecked ->
                        timarySharedPreferenceManager.setBoolean(KEY_SETTING_NOTIFICATION, isChecked)
                    }
            ))

            add(SettingItems.DeepLineItem(0))

            add(SettingItems.TitleItem(
                    R.string.setting_term,
                    context.resources.getString(R.string.setting_term),
                    View.OnClickListener {
                        Navigator.navigateToTermTextActivity(it.context)
                    }
            ))
        }

        return Single.just(items)
    }

}
