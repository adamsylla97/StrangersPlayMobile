package com.strangersplay.categories.network

import com.strangersplay.categories.model.Category
import com.strangersplay.single_event.model.SingleEvent
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCategoryService {

    @GET("/category")
    suspend fun fetchCategories(): List<Category>

}