package com.example.applogginghandle.common.model.network

import androidx.annotation.StringRes
import com.example.applogginghandle.R

enum class RetroResponseError(@StringRes val  description: Int) {
    NetWorkError(R.string.internet_error),
    UnHandledError(R.string.technical_error)
}