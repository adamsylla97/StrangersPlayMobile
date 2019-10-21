package com.strangersplay.login.view

import com.strangersplay.login.model.UserLoginData

interface LoginView {
    fun getLoginData(): UserLoginData
    fun displayToast(message: String)
    fun loginToAccount()
}