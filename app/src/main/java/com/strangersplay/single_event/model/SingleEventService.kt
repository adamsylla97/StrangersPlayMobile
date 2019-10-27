package com.strangersplay.single_event.model

class SingleEventService(private val singleEventDataProvider: SingleEventDataProvider) {
    fun getSingleEvent(): SingleEvent{
        return singleEventDataProvider.mockGetSingleEvent()
    }
}