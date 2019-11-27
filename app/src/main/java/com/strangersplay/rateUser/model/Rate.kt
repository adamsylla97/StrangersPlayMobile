package com.strangersplay.rateUser.model

data class Rate(
    val authorUsername: String,
    val description: String,
    val rate: Int
)