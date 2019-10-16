package com.strangersplay.register.model

import com.strangersplay.register.network.RestRegisterService

class RegisterDataProvider(private val restRegisterService: RestRegisterService) {

    suspend fun registerAccount(login: String, password: String, email: String) : RegisterResponse
            = restRegisterService.regsiterAccount(login,password,email)

    suspend fun mockedRegisterAccount(userRegisterData: UserRegisterData): RegisterResponse{
        if(userRegisterData.username == "abc"){
            return RegisterResponse(
                status = "success",
                message = null
            )
        } else if (userRegisterData.username == "123"){
            return RegisterResponse(
                status = "error",
                message = "login_used"
            )
        } else {
            return RegisterResponse(
                status = "error",
                message = "email_used"
            )
        }
    }

}