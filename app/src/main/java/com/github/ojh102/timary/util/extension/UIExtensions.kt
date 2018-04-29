package com.github.ojh102.timary.util.extension

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Handler
import android.support.annotation.IdRes
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.widget.Toast

fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        val toast = Toast.makeText(this, message, duration)
        toast.show()
    }
}

fun AppCompatActivity.addFragment(@IdRes containerViewId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
            .add(containerViewId, fragment, tag)
            .commitNow()
}

fun AppCompatActivity.showFragment(tag: String) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)

    supportFragmentManager.beginTransaction()
            .show(fragment)
            .commitNow()
}

fun AppCompatActivity.hideFragment(tag: String) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)

    supportFragmentManager.beginTransaction()
            .hide(fragment)
            .commitNow()
}

fun AppCompatActivity.startActivityWithTransition(intent: Intent, vararg views: View) {

    val pairs = views.map {
        Pair.create(it, it.transitionName)
    }.toTypedArray()

    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *pairs)
    startActivity(intent, options.toBundle())
}


fun ViewGroup?.inflater(): LayoutInflater {
    return LayoutInflater.from(this?.context)
}

fun View.showKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, SHOW_IMPLICIT)
    }, delay)
}

fun View.hideSoftKeyboard(delay: Long = 300) {
    Handler().postDelayed({
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromInputMethod(windowToken, 0)
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

