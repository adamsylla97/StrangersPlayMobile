package com.strangersplay.single_event.network

import com.strangersplay.single_event.model.SingleEvent
import retrofit2.http.GET
import retrofit2.http.Path

interface RestSingleEventService {

    @GET("/advertisement/{eventId}")
    suspend fun fetchSingleEventInfo(@Path("eventId") eventId: Int): SingleEvent

}