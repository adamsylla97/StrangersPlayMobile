package com.strangersplay.login.network

import com.strangersplay.login.model.LoginResponse
import com.strangersplay.login.model.UserLoginData
import retrofit2.http.*

interface RestLoginService {

    @POST("/login")
    suspend fun loginToAccount(@Body userLoginData: UserLoginData): LoginResponse

}