package com.strangersplay.register.view

import com.strangersplay.register.model.UserRegisterData

interface RegisterView {
    fun getRegisterData(): UserRegisterData
    fun showToast(message: String)
    fun finishRegistration()
}