package com.example.applogginghandle

import android.app.Application
import android.content.Context
import androidx.work.*
import com.example.applogginghandle.common.logging_event.EventManageWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        val sendLogsWorkRequest =
            // load file every 3 h
            PeriodicWorkRequestBuilder<EventManageWorker>(3, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            EventManageWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            sendLogsWorkRequest
        )
    }

    companion object {
        private lateinit var context: Context
        fun getContext(): Context {
            return context
        }

    }
}