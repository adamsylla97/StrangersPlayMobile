package com.strangersplay.background_service.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEventsEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userEventsDao(): UserEventsDao
}