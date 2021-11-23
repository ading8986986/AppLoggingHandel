package com.example.applogginghandle.common.logging_event

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.applogginghandle.MyApplication
import com.example.applogginghandle.common.logging_event.domain.DeleteEventUseCase
import com.example.applogginghandle.common.model.UseCaseResponse
import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.domain.PostEventUseCase
import com.google.gson.Gson
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class EventWorker(
    context: Context,
    parameters: WorkerParameters
) :
    CoroutineWorker(context, parameters) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface EventWorkerEntryPoint {
        fun postEventUseCase(): PostEventUseCase
        fun deleteEventUseCase(): DeleteEventUseCase
    }


    override suspend fun doWork(): Result {
        val request =
            Gson().fromJson(inputData.getString(PostEventRequestKey), PostEventRequest::class.java)
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            MyApplication.getContext(),
            EventWorkerEntryPoint::class.java
        )

        val postEventUseCase = hiltEntryPoint.postEventUseCase()
        val deleteEventUseCase = hiltEntryPoint.deleteEventUseCase()

        val result = postEventUseCase(request)
        return if (result is UseCaseResponse.Success) {
            deleteEventUseCase(request)
            Result.success()
        } else {
            Result.failure()
        }
    }

    companion object {
        const val PostEventRequestKey = "PostEventRequest"
    }
}