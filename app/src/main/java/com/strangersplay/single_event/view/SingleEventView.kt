package com.strangersplay.single_event.view

import com.strangersplay.single_event.model.SingleEvent

interface SingleEventView {
    fun displayEvent(event: SingleEvent)
    fun getEventId(): Int
    fun updateList(users: List<String>)
}