package com.strangersplay.categories.model

class CategoryService(private val dataProvider: CategoryDataProvider) {
    suspend fun getCategory(): List<Category> {
        return dataProvider.mockedFetchCategores()
    }

}