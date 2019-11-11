package com.strangersplay.add_event.network

import com.strangersplay.add_event.model.NewEventData
import com.strangersplay.add_event.model.NewEventResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RestNewEventService {
    @POST("/advertisement")
    suspend fun newEventData(@Body newEventData: NewEventData): NewEventResponse
}
