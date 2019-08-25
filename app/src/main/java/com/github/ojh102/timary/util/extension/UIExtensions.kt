package com.github.ojh102.timary.util.extension

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Context?.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null) {
        return
    }

    val toast = Toast.makeText(this, message, duration)
    toast.show()
}

fun View.showKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        requestFocus()
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }, delay)
}

fun View.hideKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }, delay)
}

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}
