package com.strangersplay.register.model

class RegisterService(private val registerDataProvider: RegisterDataProvider) {

    suspend fun registerAccount(userRegisterData: UserRegisterData): RegisterResponse{
        return registerDataProvider.registerAccount(userRegisterData)
    }

}
