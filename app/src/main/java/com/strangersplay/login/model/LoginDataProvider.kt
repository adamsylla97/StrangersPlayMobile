package com.strangersplay.login.model

import com.strangersplay.login.network.RestLoginService

class LoginDataProvider(private val restLoginService: RestLoginService) {

    suspend fun loginToAccount(username: String, password: String): LoginResponse =
        restLoginService.loginToAccount(username, password)

    suspend fun mockedLoginToAccount(username: String, password: String): LoginResponse {
        if("abc".equals(username)){
            return LoginResponse(
                status = "success",
                message = null
            )
        } else if("123".equals(username)){
            return LoginResponse(
                status = "error",
                message = "bad_login"
            )
        } else {
            return LoginResponse(
                status = "error",
                message = "no_account"
            )
        }
    }

}