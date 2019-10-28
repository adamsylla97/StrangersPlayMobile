package com.strangersplay.login.model

class LoginService(private val dataProvider: LoginDataProvider) {

    suspend fun loginToAccount(userLoginData: UserLoginData): LoginResponse {
        return dataProvider.loginToAccount(userLoginData)
    }

}