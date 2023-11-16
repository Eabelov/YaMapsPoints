package ru.netology.yamapspoints.dto

import ru.netology.yamapspoints.types.PointType

data class Point(
    val id: Long = 0,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val pointType: PointType
)