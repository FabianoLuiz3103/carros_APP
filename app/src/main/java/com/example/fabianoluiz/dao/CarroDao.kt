package com.example.fabianoluiz.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fabianoluiz.entity.Carro

@Dao
interface CarroDao {

    @Insert
    suspend fun insert(carro: Carro)

    @Query("SELECT * FROM carro ORDER BY id ASC")
    fun getAllCarro(): LiveData<List<Carro>>

    @Update
    suspend fun update(carro: Carro)

    @Delete
    suspend fun delete(carro: Carro)
}