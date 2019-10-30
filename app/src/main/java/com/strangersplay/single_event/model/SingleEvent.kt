package com.strangersplay.single_event.model

data class SingleEvent(
    val category: String,
    val creationTime: String,
    val description: String,
    val eventLocation: String,
    val eventTime: String,
    val id: Int,
    val level: Int,
    val price: Double,
    val title: String,
    val username: String
)