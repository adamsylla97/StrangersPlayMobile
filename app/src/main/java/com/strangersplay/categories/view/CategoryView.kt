package com.strangersplay.categories.view

import com.strangersplay.categories.model.Category

interface CategoryView {
    fun updateList(categories: List<Category>)
}