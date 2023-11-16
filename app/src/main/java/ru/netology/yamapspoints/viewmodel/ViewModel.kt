package ru.netology.yamapspoints.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.netology.yamapspoints.db.PointDb
import ru.netology.yamapspoints.dto.Point
import ru.netology.yamapspoints.entity.PointEntity

class ViewModel(context: Application): AndroidViewModel(context) {

    private val data = PointDb.getInstance(context).pointDao()
    val points = data.getAll().map {
        it.map(PointEntity::toDto)
    }

    fun insertPoint(point: Point) {
        viewModelScope.launch {
            data.insert(PointEntity.fromDto(point))
        }
    }

    fun removePointById(id: Long) {
        viewModelScope.launch {
            data.removeById(id)
        }
    }

}