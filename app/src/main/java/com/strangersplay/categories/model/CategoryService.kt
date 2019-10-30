package com.strangersplay.categories.model

class CategoryService(private val dataProvider: CategoryDataProvider) {

    suspend fun getCategories(): List<Category> {
        return dataProvider.fetchCategories()
    }

}