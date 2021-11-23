package com.example.applogginghandle.data.database

import androidx.room.*
import com.example.applogginghandle.data.model.PostEventRequest

@Dao
interface EventDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(postEventRequest: PostEventRequest)

    @Delete
    suspend fun deleteEvent(postEventRequest: PostEventRequest)

    @Query("SELECT * FROM postEventRequest")
    suspend fun loadFailedEvents(): List<PostEventRequest>

}