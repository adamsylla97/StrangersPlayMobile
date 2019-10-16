package com.strangersplay.register.network

import com.strangersplay.register.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RestRegisterService {

    @POST("/register")
    suspend fun regsiterAccount(@Body username: String, @Body password: String, @Body email: String):  RegisterResponse

}