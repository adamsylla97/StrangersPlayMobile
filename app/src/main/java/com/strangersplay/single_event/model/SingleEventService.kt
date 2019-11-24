package com.strangersplay.single_event.model

class SingleEventService(private val singleEventDataProvider: SingleEventDataProvider) {

    suspend fun getSingleEvent(eventId: Int): SingleEvent{
        return singleEventDataProvider.getSingleEventInfo(eventId)
    }

    suspend fun joinToEvent(eventId: Int, userToken: Int): StrangerResponse {
        return singleEventDataProvider.joinToEvent(eventId, userToken)
    }

    suspend fun leaveFromEvent(eventId: Int, userToken: Int): StrangerResponse {
        return singleEventDataProvider.leaveFromEvent(eventId, userToken)
    }

}