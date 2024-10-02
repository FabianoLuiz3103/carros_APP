package com.example.fabianoluiz

import android.app.Application
import com.example.fabianoluiz.database.CarroDatabase
import com.example.fabianoluiz.repository.CarroRepository

class CarroApplication : Application() {
    val database by lazy { CarroDatabase .getDatabase( this) }
    val repository by lazy {
        CarroRepository( database.carroDao()) }

}