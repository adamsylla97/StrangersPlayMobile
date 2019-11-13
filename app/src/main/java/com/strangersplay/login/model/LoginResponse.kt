package com.strangersplay.login.model

data class LoginResponse(
    val httpCode: Int,
    val message: String,
    val userId: Int
)