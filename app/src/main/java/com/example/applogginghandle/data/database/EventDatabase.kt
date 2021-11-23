package com.example.applogginghandle.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.applogginghandle.data.model.PostEventRequest

@Database(
    entities = [PostEventRequest::class],
    version = 1
)

abstract class EventDatabase : RoomDatabase() {
    abstract val taskDao: EventDao

    companion object {
        const val DATA_BASE_NAME = "event_database"
    }
}