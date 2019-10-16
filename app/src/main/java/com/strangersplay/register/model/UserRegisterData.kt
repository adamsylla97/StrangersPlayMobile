package com.strangersplay.register.model

data class UserRegisterData(
    val username: String,
    val password: String,
    val repeatPassword: String,
    val email: String,
    val firstName: String,
    val lastName: String
)