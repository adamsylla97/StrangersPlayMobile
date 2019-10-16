package com.strangersplay.register.model

class RegisterDataProvider {

    suspend fun mockedRegisterAccount(userRegisterData: UserRegisterData): RegisterResponse{
        if(userRegisterData.login == "abc"){
            return RegisterResponse(
                status = "success",
                message = null
            )
        } else if (userRegisterData.login == "123"){
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