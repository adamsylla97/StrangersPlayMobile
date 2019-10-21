package com.strangersplay.newest_event.model

import com.strangersplay.newest_event.network.RestNewestEventService

class NewestEventDataProvider(private val restEventServiceRest: RestNewestEventService) {

    suspend fun fetchNewestEvents(){
        //TODO implement that
    }

    suspend fun mockedFetchNewestEvent(): List<Event> {
        val event1 = Event("event1")
        val event2 = Event("event2")
        val event3 = Event("event3")

        return listOf(event1,event2,event3)
    }

}