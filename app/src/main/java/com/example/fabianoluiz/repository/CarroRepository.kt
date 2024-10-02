package com.example.fabianoluiz.repository

import androidx.lifecycle.LiveData
import com.example.fabianoluiz.dao.CarroDao
import com.example.fabianoluiz.entity.Carro

class CarroRepository (private var carroDao: CarroDao) {

    suspend fun insert(carro: Carro){
        carroDao .insert(carro)
    }

    val allCarro : LiveData<List<Carro>> = carroDao .getAllCarro()

    suspend fun update(carro: Carro){
        carroDao .update(carro)
    }

    suspend fun delete(carro: Carro){
        carroDao .delete(carro)
    }
}