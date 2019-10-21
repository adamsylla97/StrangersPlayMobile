package com.strangersplay.newest_event.view

import com.strangersplay.newest_event.model.Event

interface NewestEventView {

    fun updateList(events: List<Event>)

}