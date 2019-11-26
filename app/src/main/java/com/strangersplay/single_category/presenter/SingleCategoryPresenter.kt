package com.strangersplay.single_category.presenter

import android.util.Log
import com.strangersplay.newest_event.model.NewestEventService
import com.strangersplay.single_category.model.SingleCategoryService
import com.strangersplay.single_category.view.SingleCategoryView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SingleCategoryPresenter(private val eventsService: SingleCategoryService, private val singleCategoryView: SingleCategoryView) {

    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun fetchEvents(eventType: String){

        ioScope.launch {
            val events = eventsService.fetchEvents(eventType)
            mainScope.launch {
                singleCategoryView.updateList(events)
                Log.i("supertest cate", events.toString())
            }
        }

    }

}