package com.strangersplay.single_event.network

import com.strangersplay.single_event.model.SingleEvent
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestSingleEventService {

    @GET("/advertisement/{eventId}")
    suspend fun fetchSingleEventInfo(@Path("eventId") eventId: Int): SingleEvent

    @POST("/advertisement/{id}/join/{userId}")
    suspend fun joinToEvent(@Path("id") id: Int, @Path("userId") userId: Int)

    @DELETE("/advertisement/{id}/join/{userId}")
    suspend fun leaveFromEvent(@Path("id") id: Int, @Path("userId") userId: Int)

}