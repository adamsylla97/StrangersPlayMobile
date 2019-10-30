package com.strangersplay.single_category.view

import com.strangersplay.single_category.model.Event


interface SingleCategoryView {
    fun updateList(events: List<Event>)
}