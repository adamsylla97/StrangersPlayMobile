package com.strangersplay.single_event.model

data class SingleEvent(
    val authorId: Int,
    val category: String,
    val creationTime: String,
    val description: String,
    val eventLocation: String,
    val eventTime: String,
    val id: Int,
    val image: String,
    val level: Int,
    val price: Double,
    val title: String,
    val userIdsList: List<UserIds>,
    val userLimit: Int,
    val usersJoined: Int
)