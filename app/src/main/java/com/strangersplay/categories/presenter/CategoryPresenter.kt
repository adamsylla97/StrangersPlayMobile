package com.strangersplay.categories.presenter

import com.strangersplay.categories.model.CategoryService
import com.strangersplay.categories.view.CategoryView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CategoryPresenter(
    private val categoryService: CategoryService,
    private val categoryView: CategoryView
) {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun displayCategories() {

        ioScope.launch {
            val categories = categoryService.getCategory()
            mainScope.launch {
                categoryView.updateList(categories)
            }
        }
    }

}

