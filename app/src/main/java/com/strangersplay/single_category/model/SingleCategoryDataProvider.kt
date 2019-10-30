package com.strangersplay.single_category.model

import com.strangersplay.single_category.network.RestSingleCategoryService

class SingleCategoryDataProvider(private val restSingleCategoryService: RestSingleCategoryService) {

    suspend fun fetchEvents(): List<Event> {
        return restSingleCategoryService.fetchEventsList()
    }

}