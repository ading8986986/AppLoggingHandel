package com.example.applogginghandle.data.database

import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.domain.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: EventDao) : LocalRepository {
    override suspend fun addEvent(postEventRequest: PostEventRequest) {
        dao.addEvent(postEventRequest)
    }

    override suspend fun deleteEvent(postEventRequest: PostEventRequest) {
        dao.deleteEvent(postEventRequest)
    }

    override suspend fun loadFailedEvents(): List<PostEventRequest> {
        return dao.loadFailedEvents()
    }
}