package ru.netology.yamapspoints.listener

import ru.netology.yamapspoints.dto.Point

interface OnInteractionListener {
    fun onClick(point: Point)
    fun onRemove(point: Point)
    fun onEdit(point: Point)
}