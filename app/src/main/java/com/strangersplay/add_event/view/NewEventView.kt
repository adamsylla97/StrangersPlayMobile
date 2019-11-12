package com.strangersplay.add_event.view

import com.strangersplay.add_event.model.NewEventData

interface NewEventView {
    fun getEventData(): NewEventData
    fun finishAdding()
}