package com.strangersplay.joined_events.view

import com.strangersplay.newest_event.model.Event

interface JoinedEventView {
    fun updateList(event: Event)
}