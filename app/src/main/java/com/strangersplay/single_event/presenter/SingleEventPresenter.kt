package com.strangersplay.single_event.presenter

import android.util.Log
import com.strangersplay.Config
import com.strangersplay.single_event.model.SingleEventService
import com.strangersplay.single_event.model.StrangerResponse
import com.strangersplay.single_event.view.SingleEventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class SingleEventPresenter(private val singleEventService: SingleEventService, private val singleEventView: SingleEventView) {
    private val job = Job()

    private val ioContext = job + Dispatchers.IO
    private val ioScope = CoroutineScope(ioContext)

    private val mainContext = job + Dispatchers.Main
    private val mainScope = CoroutineScope(mainContext)

    fun displaySingleEvent(){
        ioScope.launch {
            val eventId = singleEventView.getEventId()
            val singleEventInformation = singleEventService.getSingleEvent(eventId)
            mainScope.launch {
                singleEventView.displayEvent(singleEventInformation)
                singleEventView.updateList(singleEventInformation.userIdsList)
            }
        }
    }

    fun joinToEvent() {
        ioScope.launch {
            val eventId = singleEventView.getEventId()
            try{
                 singleEventService.joinToEvent(eventId, Config.userToken)
            } catch (e: Exception){
                mainScope.launch {
                    Log.i("supertest123", e.message)
                    singleEventView.displayToast("You have already joined this event.")
                }
            }

        }
    }

    fun leaveEvent() {
        ioScope.launch {
            val eventId = singleEventView.getEventId()
            try{
                val response = singleEventService.leaveFromEvent(eventId, Config.userToken)
                Log.i("supertest123", response.message)
            } catch (e: Exception){
                Log.i("supertest123", e.message)
            }

        }
    }

    fun finishThreads(){
        job.complete()
    }


}