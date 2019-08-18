package com.github.ojh102.timary.util

import android.content.Context
import android.content.Intent
import com.github.ojh102.timary.R
import com.github.ojh102.timary.ui.common.TextActivity
import com.github.ojh102.timary.ui.complete.CompleteActivity
import com.github.ojh102.timary.ui.complete.CompleteType
import com.github.ojh102.timary.ui.main.MainActivity
import com.github.ojh102.timary.ui.read.ReadActivity
import com.github.ojh102.timary.ui.write.content.WriteActivity
import com.github.ojh102.timary.ui.write.store.StoreActivity

class Navigator {

    companion object {

        @JvmStatic
        fun navigateToMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        @JvmStatic
        fun navigateToWriteActivity(context: Context?) {
            context?.startActivity(Intent(context, WriteActivity::class.java))
        }

        @JvmStatic
        fun navigateToCompleteActivity(context: Context, type: CompleteType, title: String, description: String? = null, isClear: Boolean = false) {
            context.startActivity(Intent(context, CompleteActivity::class.java).apply {
                putExtra(KEY_COMPLETE_TYPE, type)
                putExtra(KEY_COMPLETE_TITLE, title)
                description?.let {
                    putExtra(KEY_COMPLETE_DESCRIPTION, it)
                }
                if (isClear) {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            })
        }

        @JvmStatic
        fun navigateToStoreActivity(context: Context, content: String) {
            context.startActivity(Intent(context, StoreActivity::class.java).apply {
                putExtra(KEY_CAPSULE_CONTENT, content)
            })
        }

        @JvmStatic
        fun navigateToTermTextActivity(context: Context) {
            val title = context.getString(R.string.setting_term)
            val content = context.getString(R.string.term_content)

            navigateToTextActivity(context, title, content)
        }

        @JvmStatic
        fun navigateToTextActivity(context: Context, title: String, content: String) {
            context.startActivity(Intent(context, TextActivity::class.java).apply {
                putExtra(KEY_TEXT_TITLE, title)
                putExtra(KEY_TEXT_CONTENT, content)
            })
        }

        @JvmStatic
        fun navigateToReadActivity(context: Context, capsuleId: Long) {
            context.startActivity(Intent(context, ReadActivity::class.java).apply {
                putExtra(KEY_CAPSULE_ID, capsuleId)
            })
        }
    }
}
