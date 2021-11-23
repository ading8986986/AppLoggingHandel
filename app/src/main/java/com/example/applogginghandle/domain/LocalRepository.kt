package com.example.applogginghandle.domain

import com.example.applogginghandle.data.model.PostEventRequest

interface LocalRepository {
    suspend fun addEvent(postEventRequest: PostEventRequest)
    suspend fun deleteEvent(postEventRequest : PostEventRequest)
    suspend fun loadFailedEvents(): List<PostEventRequest>
}