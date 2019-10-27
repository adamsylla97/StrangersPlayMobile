package com.strangersplay.single_event.model

import com.strangersplay.single_event.network.RestSingleEventService

class SingleEventDataProvider(private val restSingleEventService: RestSingleEventService) {

    fun mockGetSingleEvent(): SingleEvent{
        return SingleEvent("Test",
            "https://cdn.pixabay.com/photo/2014/06/03/19/38/road-sign-361514_960_720.png",
            1, "Lodz",
            10.0f)
    }
}