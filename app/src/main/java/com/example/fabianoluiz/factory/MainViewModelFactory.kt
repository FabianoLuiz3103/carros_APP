package com.example.fabianoluiz.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fabianoluiz.MainViewModel
import com.example.fabianoluiz.repository.CarroRepository

class MainViewModelFactory (private val carroRepository: CarroRepository)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom( MainViewModel ::class.java)) {
            @Suppress("UNCHECKED_CAST" )
            return MainViewModel(carroRepository) as T
        }
        throw IllegalArgumentException( "Unknown ViewModel class" )
    }
}