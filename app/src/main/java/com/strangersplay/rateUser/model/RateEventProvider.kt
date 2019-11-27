package com.strangersplay.rateUser.model

import com.strangersplay.rateUser.network.RestAddRateService
import com.strangersplay.single_event.model.StrangerResponse

class RateEventProvider(private val restAddRateService: RestAddRateService) {

suspend fun addEventRate(userName: String,rate:Rate):StrangerResponse {
    return restAddRateService.addRate(userName,rate)
}}