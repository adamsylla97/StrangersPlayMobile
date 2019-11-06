package com.strangersplay.user_profile.model

data class UserData(val firstName: String,
                    val lastName: String,
                    val emailAdress: String,
                    val level: String,
                    val about: String,
                    val photo: String,
                    val comments: List<Comment>)