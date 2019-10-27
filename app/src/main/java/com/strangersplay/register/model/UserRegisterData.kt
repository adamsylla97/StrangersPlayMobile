package com.strangersplay.register.model

data class UserRegisterData(
    val active: Boolean,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val username: String
)