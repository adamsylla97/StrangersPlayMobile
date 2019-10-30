package com.strangersplay.register.model

import com.strangersplay.register.network.RestRegisterService

class RegisterDataProvider(private val restRegisterService: RestRegisterService) {

    suspend fun registerAccount(userRegisterData: UserRegisterData) : RegisterResponse
            = restRegisterService.regsiterAccount(userRegisterData)

}