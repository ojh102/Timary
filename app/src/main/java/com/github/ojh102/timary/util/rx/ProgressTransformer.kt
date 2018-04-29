package com.github.ojh102.timary.util.rx

import android.content.Context
import com.github.ojh102.timary.ui.widget.TimaryProgressDialog
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer

class ProgressTransformer<T>(private val context: Context) : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        val progressDialog = TimaryProgressDialog(context).also { it.show() }
        return upstream.doAfterTerminate(progressDialog::dismiss)
    }

}