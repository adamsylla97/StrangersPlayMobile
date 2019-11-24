package com.strangersplay.categories.network

import com.strangersplay.categories.model.Category
import retrofit2.http.GET

interface RestCategoryService {

    @GET("/category")
    suspend fun fetchCategories(): List<Category>

}