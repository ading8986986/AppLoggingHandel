package com.example.applogginghandle.domain

import android.util.Log
import com.example.applogginghandle.common.model.Constants
import com.example.applogginghandle.common.model.network.RepositoryResponse
import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.data.model.PostEventResponse
import com.example.applogginghandle.common.model.UseCaseResponse
import javax.inject.Inject

class PostEventUseCase @Inject constructor(private val repository: RemoteRepository){
    suspend operator fun invoke(postEventRequest: PostEventRequest) : UseCaseResponse<PostEventResponse>{
        Log.d(Constants.EVENT_TAG, "PostEventUseCase $postEventRequest.timeStamp")
        val result  = repository.postEvent(postEventRequest)
        return if(result.isSuccess()){
            UseCaseResponse.Success((result as RepositoryResponse.Success).data)
        } else {
            UseCaseResponse.Error((result as RepositoryResponse.Error).error.description)
        }
    }


}