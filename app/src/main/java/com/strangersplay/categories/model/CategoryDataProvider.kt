package com.strangersplay.categories.model

import com.strangersplay.categories.network.RestCategoryService

class CategoryDataProvider(private val restCategoryService: RestCategoryService) {

    suspend fun fetchCategories(): List<Category>{
        return restCategoryService.fetchCategories()
    }

}