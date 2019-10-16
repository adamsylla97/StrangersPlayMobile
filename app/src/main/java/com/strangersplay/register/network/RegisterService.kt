package com.strangersplay.register.network

import com.strangersplay.register.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("/register")
    fun regsiterAccount(@Body login: String, @Body password: String, @Body email: String):  RegisterResponse

}