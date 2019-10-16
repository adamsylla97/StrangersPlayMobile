package com.strangersplay.login.model

class LoginService(private val dataProvider: LoginDataProvider) {

    suspend fun loginToAccount(username: String, password: String): LoginResponse {
        return dataProvider.mockedLoginToAccount(username, password)
    }

}