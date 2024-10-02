package com.example.fabianoluiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fabianoluiz.dao.CarroDao
import com.example.fabianoluiz.entity.Carro

@Database(entities = [Carro::class], version = 1)
abstract class CarroDatabase : RoomDatabase() {

    abstract fun carroDao(): CarroDao
    companion object {
        @Volatile
        private var INSTANCE: CarroDatabase? = null
        fun getDatabase(context: Context): CarroDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarroDatabase::class.java,
                    "carro_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}