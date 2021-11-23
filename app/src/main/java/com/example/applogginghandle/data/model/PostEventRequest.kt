package com.example.applogginghandle.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class PostEventRequest(
    val tag: String,
    val body: String,
    val timeStamp: String,
    val appID: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Serializable
