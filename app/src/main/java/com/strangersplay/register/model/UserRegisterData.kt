package com.strangersplay.register.model

data class UserRegisterData(
    val login: String,
    val password: String,
    val repeatPassword: String,
    val email: String
)