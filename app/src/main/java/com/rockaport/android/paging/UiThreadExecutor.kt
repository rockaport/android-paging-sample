package com.rockaport.android.paging

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class UiThreadExecutor : Executor {
    private val handler = Handler(Looper.getMainLooper())

    override fun execute(runnable: Runnable?) {
        handler.post(runnable)
    }
}