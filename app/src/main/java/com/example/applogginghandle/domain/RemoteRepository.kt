package com.example.applogginghandle.domain

import com.example.applogginghandle.common.model.network.RepositoryResponse
import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.data.model.PostEventResponse

interface RemoteRepository {
    suspend fun postEvent(postEventRequest: PostEventRequest): RepositoryResponse<PostEventResponse>
}