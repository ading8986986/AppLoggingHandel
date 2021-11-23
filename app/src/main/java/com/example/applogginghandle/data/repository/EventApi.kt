package com.example.applogginghandle.data.repository

import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.data.model.PostEventResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EventApi {
    @POST("./")
    suspend fun postEvent(@Body request: PostEventRequest) : Response<PostEventResponse>
}