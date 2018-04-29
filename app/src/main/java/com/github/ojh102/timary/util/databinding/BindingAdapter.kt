package com.github.ojh102.timary.util.databinding

import android.databinding.BindingAdapter
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.ColorDrawable
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.content.res.AppCompatResources
import android.text.method.MovementMethod
import android.view.View
import android.widget.TextView
import com.github.ojh102.timary.util.extension.afterMeasured
import com.github.ojh102.timary.util.resources.TimaryResourcesUtil
import io.reactivex.functions.Action

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(textView: TextView, @StringRes resId: Int) {
        textView.setText(resId)
    }

    @JvmStatic
    @BindingAdapter("android:textColor")
    fun setTextColor(textView: TextView, colorOrResId: Int) {
        if (TimaryResourcesUtil.hasResource(colorOrResId)) {
            val resId = if (colorOrResId == 0) {
                null
            } else {
                AppCompatResources.getColorStateList(textView.context, colorOrResId)
            }

            textView.setTextColor(resId)
        } else {
            textView.setTextColor(colorOrResId)
        }
    }

    @JvmStatic
    @BindingAdapter("textGradientStart", "textGradientEnd")
    fun setGradientText(textView: TextView, startColorRes: Int, endColorRes: Int) {
        textView.afterMeasured {
            paint.shader = LinearGradient(
                    0f,
                    0f,
                    width.toFloat(),
                    height.toFloat(),
                    ContextCompat.getColor(context, startColorRes),
                    ContextCompat.getColor(context, endColorRes),
                    Shader.TileMode.CLAMP
            )
        }
    }

    @JvmStatic
    @BindingAdapter("android:textAppearance")
    fun setTextAppearance(textView: TextView, @StyleRes resId: Int) {
        if (resId == 0) {
            return
        }

        TextViewCompat.setTextAppearance(textView, resId)
    }

    @JvmStatic
    @BindingAdapter("movementMethod")
    fun setMovementMethod(textView: TextView, movementMethod: MovementMethod) {
        textView.movementMethod = movementMethod
    }

    @JvmStatic
    @BindingAdapter("android:background")
    fun setBackground(view: View, colorOrResId: Int) {
        if (TimaryResourcesUtil.hasResource(colorOrResId)) {
            ViewCompat.setBackground(view, AppCompatResources.getDrawable(view.context, colorOrResId))
        } else {
            ViewCompat.setBackground(view, ColorDrawable(colorOrResId))
        }
    }

    @JvmStatic
    @BindingAdapter("android:onEditorAction")
    fun setOnEditorAction(textView: TextView, onEditorAction: Action) {
        textView.setOnEditorActionListener { _, _, _ ->
            onEditorAction.run()
            false
        }
    }
}