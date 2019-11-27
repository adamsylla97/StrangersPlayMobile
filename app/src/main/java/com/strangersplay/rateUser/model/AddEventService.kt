package com.strangersplay.rateUser.model

import com.strangersplay.single_event.model.SingleEvent
import com.strangersplay.single_event.model.SingleEventDataProvider
import com.strangersplay.single_event.model.StrangerResponse

class AddEventService(private val rateEventProvider: RateEventProvider) {
    suspend fun addEventRate(userName:String,rate:Rate):StrangerResponse {
        return rateEventProvider.addEventRate(userName,rate)
    }

}