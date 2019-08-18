package com.github.ojh102.timary.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.github.ojh102.timary.R

class TimaryProgressDialog(context: Context) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setContentView(R.layout.dialog_progress)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
