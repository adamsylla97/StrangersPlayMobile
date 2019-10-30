package com.strangersplay.single_event.network

import com.strangersplay.login.model.LoginResponse
import com.strangersplay.login.model.UserLoginData
import com.strangersplay.single_event.model.SingleEvent
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestSingleEventService {

    @GET("/advertisement/{eventId}")
    suspend fun fetchSingleEventInfo(@Path("eventId") eventId: Int): SingleEvent

}