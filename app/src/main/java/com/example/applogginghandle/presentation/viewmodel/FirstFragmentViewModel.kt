package com.example.applogginghandle.presentation.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applogginghandle.R
import com.example.applogginghandle.common.model.Event
import com.example.applogginghandle.common.model.UseCaseResponse
import com.example.applogginghandle.data.model.PostEventRequest
import com.example.applogginghandle.domain.PostEventUseCase
import com.example.applogginghandle.domain.SaveFailedEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    private val postEventUseCase: PostEventUseCase,
    private val saveFailedEventUseCase: SaveFailedEventUseCase
) : ViewModel() {

    private val _hint = MutableLiveData<Event<Int>>()
    val hint: LiveData<Event<Int>> = _hint

    fun onPostEvent(tag: String, body: String) {
        viewModelScope.launch {
            val request = PostEventRequest(
                tag,
                body,
                Date().time.toString(),
                Build.ID
            )
            val result = postEventUseCase(request)
            if (result is UseCaseResponse.Error) {
                saveFailedEventUseCase(request)
                _hint.value = Event(R.string.error)
            } else {
                _hint.value = Event(R.string.success)
            }
        }
    }

}