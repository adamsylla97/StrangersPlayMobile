package com.strangersplay.add_event.view

import com.strangersplay.add_event.model.NewEventData
import com.strangersplay.categories.model.Category

interface NewEventView {
    fun getEventData(): NewEventData
    fun setupCategorySpinner(categories: List<Category>)
    fun setupCalender()
    fun setupClock()
}