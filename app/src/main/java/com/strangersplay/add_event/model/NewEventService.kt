package com.strangersplay.add_event.model

class NewEventService(private val newEventDataProvider: NewEventDataProvider) {
    suspend fun newEventData(newEventData: NewEventData): NewEventResponse {


        return newEventDataProvider.setNewEventInfo(newEventData)
    }
}

