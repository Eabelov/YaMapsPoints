package ru.netology.yamapspoints.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.yamapspoints.entity.PointEntity
import ru.netology.yamapspoints.dao.PointDao

@Database(entities = [PointEntity::class], version = 1, exportSchema = false)
abstract class PointDb: RoomDatabase() {
    abstract fun pointDao(): PointDao

    companion object {
        @Volatile
        private var instance: PointDb? = null

        fun getInstance(context: Context): PointDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PointDb::class.java, "point.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}