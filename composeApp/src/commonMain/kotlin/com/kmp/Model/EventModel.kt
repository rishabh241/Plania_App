package com.kmp.Model

class EventModel(
    val title: String?,
    val description: String?,
    val rating: Double?,
    val location: String?,
    val price: String?,
    val imageUrl: String?,
    val isFavorite: Boolean?,
    val TransportList: List<TranspostModel>,
    val startTime: String,
    val endTime: String
)