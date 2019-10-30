package com.strangersplay.single_category.network

import com.strangersplay.single_category.model.Event
import retrofit2.http.GET

interface RestSingleCategoryService {

    @GET("/advertisement")
    suspend fun fetchEventsList(): List<Event>

}