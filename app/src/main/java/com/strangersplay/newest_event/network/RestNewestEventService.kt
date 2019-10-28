package com.strangersplay.newest_event.network

import com.strangersplay.login.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestNewestEventService {

    @GET("/advertisment/{id}")
    suspend fun loginToAccount(@Body username: String, @Body password: String): LoginResponse

}