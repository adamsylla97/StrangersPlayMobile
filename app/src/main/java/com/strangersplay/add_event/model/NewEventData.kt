package com.strangersplay.add_event.model

data class NewEventData(
    val authorId: Int,
    val category: String,
    val creationTime: String,
    val description: String,
    val eventLocation: String,
    val eventTime: String,
    val id: Int,
    val image: List<String>,
    val level: Int,
    val price: Int,
    val title: String,
    val userLimit: Int
)