package com.example.applogginghandle.data.repository

import com.example.applogginghandle.common.model.network.RetroResponseError
import com.example.applogginghandle.common.model.network.RepositoryResponse
import com.example.applogginghandle.common.util.RetroHelper
import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.data.model.PostEventResponse
import com.example.applogginghandle.domain.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val eventApi: EventApi): RemoteRepository {
    override suspend fun postEvent(postEventRequest: PostEventRequest): RepositoryResponse<PostEventResponse> {
        return try {
            val response =  eventApi.postEvent(postEventRequest)
            if(response.isSuccessful && response.body()?.accepted == true){
                RepositoryResponse.Success(response.body()!!)
            } else {
                RepositoryResponse.Error(RetroResponseError.UnHandledError)
            }
        } catch (t:Throwable) {
            RepositoryResponse.Error(RetroHelper.parseThrowable(t))
        }
    }

}