package com.strangersplay.newest_event.model

data class Event(
    val category: String,
    val creationTime: String,
    val eventLocation: String,
    val eventTime: String,
    val id: Int,
    val level: Int, //gwiazdki
    val price: Int,
    val title: String
)