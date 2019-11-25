package com.strangersplay.newest_event.model

import com.strangersplay.add_event.model.UserIds

data class Event(
    val authorId: Int,
    val category: String,
    val creationTime: String,
    val description: String,
    val eventLocation: String,
    val eventTime: String,
    val id: Int,
    val image: Any,
    val level: Int,
    val price: Double,
    val title: String,
    val userIdsList: List<UserIds>,
    val userLimit: Int
)