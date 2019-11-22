package com.strangersplay.single_event.model

import com.strangersplay.single_event.network.RestSingleEventService

class SingleEventDataProvider(private val restSingleEventService: RestSingleEventService) {

    suspend fun getSingleEventInfo(id: Int): SingleEvent {
        return restSingleEventService.fetchSingleEventInfo(id)
    }

    suspend fun joinToEvent(eventId: Int, userToken: Int) {
        restSingleEventService.joinToEvent(eventId, userToken)
    }

    suspend fun leaveFromEvent(eventId: Int, userToken: Int) {
        restSingleEventService.leaveFromEvent(eventId, userToken)
    }

}