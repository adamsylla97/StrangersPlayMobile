package com.strangersplay.background_service.database

import androidx.room.*

@Dao
interface UserEventsDao {

    @Query("SELECT * FROM `table` WHERE id = :id")
    fun findEventById(id: Int): UserEventsEntity

    @Query("SELECT * FROM `table`")
    fun getAllData():List<UserEventsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEvent: UserEventsEntity)

    @Query("DELETE FROM `table`")
    fun deleteAll()
}