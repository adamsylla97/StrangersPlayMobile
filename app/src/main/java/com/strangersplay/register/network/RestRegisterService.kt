package com.strangersplay.register.network

import com.strangersplay.register.model.RegisterResponse
import com.strangersplay.register.model.UserRegisterData
import retrofit2.http.Body
import retrofit2.http.POST

interface RestRegisterService {

    @POST("/register")
    suspend fun regsiterAccount(@Body userRegisterData: UserRegisterData): RegisterResponse

}