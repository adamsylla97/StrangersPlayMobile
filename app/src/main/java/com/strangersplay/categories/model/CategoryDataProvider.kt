package com.strangersplay.categories.model

import com.strangersplay.categories.network.RestCategoryService

class CategoryDataProvider(private val restCategoryService: RestCategoryService) {
    suspend fun fetchCategories(){
        //TODO implement that
    }

    suspend fun mockedFetchCategores(): List<Category> {
        val category1 = Category("category1")
        return listOf(category1)
    }


}