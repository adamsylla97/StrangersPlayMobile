package com.strangersplay.login.network

import com.strangersplay.login.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RestLoginService {

    @POST("/loginSuccessful")
    suspend fun loginToAccount(@Body username: String, @Body password: String): LoginResponse

}