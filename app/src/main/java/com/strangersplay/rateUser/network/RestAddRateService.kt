package com.strangersplay.rateUser.network

import com.strangersplay.rateUser.model.Rate
import com.strangersplay.single_event.model.StrangerResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path


interface RestAddRateService {
    @POST("/comment/{userName}")
    suspend fun addRate(@Path("userName") userName: String, @Body rate: Rate): StrangerResponse
}