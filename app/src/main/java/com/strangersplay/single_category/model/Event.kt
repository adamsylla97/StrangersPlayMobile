package com.strangersplay.single_category.model

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