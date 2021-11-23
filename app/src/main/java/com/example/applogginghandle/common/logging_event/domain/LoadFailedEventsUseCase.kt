package com.example.applogginghandle.common.logging_event.domain

import android.util.Log
import com.example.applogginghandle.common.model.Constants
import com.example.applogginghandle.data.database.EventDao
import com.example.applogginghandle.data.model.PostEventRequest
import javax.inject.Inject

class LoadFailedEventsUseCase @Inject constructor(
    private val dao: EventDao
) {
    suspend operator fun invoke(): List<PostEventRequest> {
        val result =  dao.loadFailedEvents()
        for(request in result){
            Log.d(Constants.EVENT_TAG, "LoadFailedEventsUseCase $request.timeStamp")
        }
        return result
    }
}