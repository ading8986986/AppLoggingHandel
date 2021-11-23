package com.example.applogginghandle.domain

import android.util.Log
import com.example.applogginghandle.common.model.Constants.EVENT_TAG
import com.example.applogginghandle.data.database.EventDao
import com.example.applogginghandle.data.model.PostEventRequest
import javax.inject.Inject

class SaveFailedEventUseCase @Inject constructor(private val dao: EventDao) {

    suspend operator fun invoke(request: PostEventRequest) {
        Log.d(EVENT_TAG, "SaveFailedEventUseCase $request.timeStamp")
        dao.addEvent(request)
    }
}