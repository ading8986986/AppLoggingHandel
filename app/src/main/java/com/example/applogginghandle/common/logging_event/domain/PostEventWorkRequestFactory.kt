package com.example.applogginghandle.common.logging_event.domain

import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import com.example.applogginghandle.common.logging_event.EventWorker
import com.example.applogginghandle.common.logging_event.EventWorker.Companion.PostEventRequestKey
import com.example.applogginghandle.data.model.PostEventRequest
import com.google.gson.Gson

object PostEventWorkRequestFactory {

    fun create(
        postEventRequest: PostEventRequest,
    ): OneTimeWorkRequest {
        val work = OneTimeWorkRequest.Builder(EventWorker::class.java)
        val data = Data.Builder()
        data.putString(PostEventRequestKey, Gson().toJson(postEventRequest))
        work.setInputData(data.build())
        return work.build()
    }
}
