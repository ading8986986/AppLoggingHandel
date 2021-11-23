package com.example.applogginghandle.common.logging_event

import android.content.Context
import androidx.work.*
import com.example.applogginghandle.MyApplication
import com.example.applogginghandle.common.logging_event.domain.LoadFailedEventsUseCase
import com.example.applogginghandle.common.logging_event.domain.PostEventWorkRequestFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class EventManageWorker(
    context: Context,
    parameters: WorkerParameters,
) :
    CoroutineWorker(context, parameters) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface EventManageWorkerEntryPoint {
        fun loadFailedEventsUseCase(): LoadFailedEventsUseCase
        fun workManager(): WorkManager
    }

    override suspend fun doWork(): Result {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            MyApplication.getContext(),
            EventManageWorkerEntryPoint::class.java
        )
        val loadFailedEventsUseCase = hiltEntryPoint.loadFailedEventsUseCase()

        val result = kotlin.runCatching {
            val failedEvents =loadFailedEventsUseCase()
            for (request in failedEvents) {
                val worker = PostEventWorkRequestFactory.create(request)
                hiltEntryPoint.workManager()
                    .enqueueUniqueWork(request.timeStamp, ExistingWorkPolicy.REPLACE, worker)
            }
        }

        return if (result.isSuccess) Result.success() else Result.failure()
    }

    companion object{
        const val WORK_NAME= "EventManageWorker"

    }



}