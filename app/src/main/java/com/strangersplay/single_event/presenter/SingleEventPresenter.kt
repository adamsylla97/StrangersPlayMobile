package com.strangersplay.single_event.presenter

import com.strangersplay.single_event.model.SingleEventService
import com.strangersplay.single_event.view.SingleEventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SingleEventPresenter(private val singleEventService: SingleEventService, private val singleEventView: SingleEventView) {
    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun displaySingleEvent(){
        ioScope.launch {
            val event = singleEventService.getSingleEvent()
            mainScope.launch {
                singleEventView.displayEvent(event)
            }
        }
    }
}