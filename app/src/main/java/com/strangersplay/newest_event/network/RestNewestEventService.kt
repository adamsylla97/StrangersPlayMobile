package com.strangersplay.newest_event.network

import com.strangersplay.newest_event.model.Event
import retrofit2.http.GET

interface RestNewestEventService {

    @GET("/advertisement")
    suspend fun fetchEventsList(): List<Event>

}