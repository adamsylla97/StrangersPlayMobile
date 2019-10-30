package com.strangersplay.single_category.model

class SingleCategoryService(private val dataProvider: SingleCategoryDataProvider) {

    suspend fun fetchEvents(eventType: String): List<Event> {
        val events = dataProvider.fetchEvents()
        return filterEvents(events,eventType)
    }

    private fun filterEvents(events: List<Event>, eventType: String): List<Event> {
        val filteredEvents = mutableListOf<Event>()
        events.forEach {
            if(it.category == eventType)
                filteredEvents.add(it)
        }
        return filteredEvents
    }

}