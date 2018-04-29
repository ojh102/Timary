package com.github.ojh102.timary.util.rx

import android.os.HandlerThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

object TimarySchedulers {

    private val REALM_HANDLER_THREAD: HandlerThread = createAndStart("DiaryRealm")
    private val realmIO: Scheduler = AndroidSchedulers.from(REALM_HANDLER_THREAD.looper)

    private fun createAndStart(name: String): HandlerThread {
        val handlerThread = HandlerThread(name)
        handlerThread.start()

        return handlerThread
    }

    @Synchronized
    fun realmIO(): Scheduler {
        return realmIO
    }
}