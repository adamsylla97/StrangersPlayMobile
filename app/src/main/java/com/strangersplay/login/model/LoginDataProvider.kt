package com.strangersplay.login.model

import com.strangersplay.login.network.RestLoginService

class LoginDataProvider(private val restLoginService: RestLoginService) {

    suspend fun loginToAccount(userLoginData: UserLoginData): LoginResponse =
        restLoginService.loginToAccount(userLoginData)

}