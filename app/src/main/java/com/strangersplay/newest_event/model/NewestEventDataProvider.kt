package com.strangersplay.newest_event.model

import com.strangersplay.newest_event.network.RestNewestEventService

class NewestEventDataProvider(private val restEventServiceRest: RestNewestEventService) {

    suspend fun fetchNewestEvents(): List<Event> {
        return restEventServiceRest.fetchEventsList()
    }

    suspend fun mockedFetchNewestEvent(): List<Event> {
        return emptyList()
    }

}