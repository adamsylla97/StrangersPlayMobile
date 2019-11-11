package com.strangersplay.add_event.model

data class NewEvent(
    val category: String,
    val creationTime: String,
    val description: String,
    val eventLocation: String,
    val eventTime: String,
    val id: Int,
    val level: Int,
    val price: Int,
    val title: String,
    val username: String
)
