package com.strangersplay.single_event.model

class SingleEventService(private val singleEventDataProvider: SingleEventDataProvider) {

    suspend fun getSingleEvent(eventId: Int): SingleEvent{
        return singleEventDataProvider.getSingleEventInfo(eventId)
    }

}