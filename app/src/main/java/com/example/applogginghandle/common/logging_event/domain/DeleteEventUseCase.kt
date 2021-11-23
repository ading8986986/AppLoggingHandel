package com.example.applogginghandle.common.logging_event.domain

import android.util.Log
import com.example.applogginghandle.common.model.Constants
import com.example.applogginghandle.data.database.EventDao
import com.example.applogginghandle.data.model.PostEventRequest
import javax.inject.Inject

class DeleteEventUseCase @Inject constructor(
    private val dao: EventDao
) {
    suspend operator fun invoke(request: PostEventRequest) {
        Log.d(Constants.EVENT_TAG, "DeleteEventUseCase $request.timeStamp")
        dao.deleteEvent(request)
    }
}