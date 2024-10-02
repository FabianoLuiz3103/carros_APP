package com.example.fabianoluiz.entity

import android.graphics.drawable.AnimatedImageDrawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carro")
data class Carro(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val marca: String,
    val modelo: String,
    val urlImage: String
)
