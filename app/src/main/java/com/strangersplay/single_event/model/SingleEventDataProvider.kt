package com.strangersplay.single_event.model

import com.strangersplay.single_event.network.RestSingleEventService

class SingleEventDataProvider(private val restSingleEventService: RestSingleEventService) {

    suspend fun getSingleEventInfo(id: Int): SingleEvent {
        return restSingleEventService.fetchSingleEventInfo(id)
    }

}