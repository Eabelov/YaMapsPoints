package ru.netology.yamapspoints.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.yamapspoints.dto.Point
import ru.netology.yamapspoints.types.PointType

@Entity
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val pointType: PointType
) {
    companion object {
        fun fromDto(dto: Point): PointEntity = with(dto) {
            PointEntity(
                id = id,
                title = title,
                description = description,
                latitude = latitude,
                longitude = longitude,
                pointType = pointType
            )
        }
    }

    fun toDto(): Point = Point(
        id = id,
        title = title,
        description = description,
        latitude = latitude,
        longitude = longitude,
        pointType = pointType
    )

}