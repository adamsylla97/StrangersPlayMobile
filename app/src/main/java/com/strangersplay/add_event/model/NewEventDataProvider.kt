package com.strangersplay.add_event.model

import com.strangersplay.add_event.network.RestNewEventService

class NewEventDataProvider(private val restNewEventService: RestNewEventService) {
    suspend fun setNewEventInfo(newEventData: NewEventData): NewEventResponse =
         restNewEventService.newEventData(newEventData)

}