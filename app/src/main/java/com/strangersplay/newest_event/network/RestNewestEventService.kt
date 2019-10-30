package com.strangersplay.newest_event.network

import com.strangersplay.login.model.LoginResponse
import com.strangersplay.newest_event.model.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestNewestEventService {

    @GET("/advertisement")
    suspend fun fetchEventsList(): List<Event>

}