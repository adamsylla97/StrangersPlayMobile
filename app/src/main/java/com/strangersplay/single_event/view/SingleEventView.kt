package com.strangersplay.single_event.view

import com.strangersplay.single_event.model.SingleEvent
import com.strangersplay.single_event.model.UserIds

interface SingleEventView {
    fun displayEvent(event: SingleEvent)
    fun getEventId(): Int
    fun updateList(users: List<UserIds>)
    fun displayToast(msg: String)
}