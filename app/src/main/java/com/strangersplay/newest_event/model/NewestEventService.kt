package com.strangersplay.newest_event.model

class NewestEventService(private val dataProvider: NewestEventDataProvider) {

    suspend fun getNewestItems(): List<Event> {
        return dataProvider.fetchNewestEvents()
    }

}