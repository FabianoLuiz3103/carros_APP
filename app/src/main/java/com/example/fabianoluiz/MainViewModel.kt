package com.example.fabianoluiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabianoluiz.entity.Carro
import com.example.fabianoluiz.repository.CarroRepository
import kotlinx.coroutines.launch

class MainViewModel(private val carroRepository: CarroRepository)
    : ViewModel(){

        fun insert(carro: Carro){
            viewModelScope.launch {
                carroRepository.insert(carro)
            }
        }

    val allCarros: LiveData<List<Carro>> = carroRepository.allCarro

    fun update(carro: Carro){
        viewModelScope.launch {
            carroRepository.update(carro)
        }
    }

    fun delete(carro: Carro){
        viewModelScope.launch {
            carroRepository.delete(carro)
        }
    }
}