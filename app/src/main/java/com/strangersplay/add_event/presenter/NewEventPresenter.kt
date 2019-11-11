package com.strangersplay.add_event.presenter

import android.util.Log
import com.strangersplay.add_event.model.NewEventService
import com.strangersplay.add_event.network.RestNewEventService
import com.strangersplay.add_event.view.NewEventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class NewEventPresenter(
    private val newEventView: NewEventView,
    private val newEventService: NewEventService
) {
    private val registerJob = Job()

    private val ioContext = registerJob + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = registerJob + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun setNewEventInfo() {
        val event = newEventView.getEventData()
        ioScope.launch {
            try {
                val response = newEventService.newEventData(event)
                if (201 == response.httpCode) {
                    Log.i("supertest123", "adding event finish succesfully")
                    newEventView.finishAdding()
                }
            } catch (e: Exception) {
                Log.i("supertest123", "new  event exception")
            }
        }
    }
}