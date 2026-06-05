package com.example.kursach.ui.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.di.AppContainer
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = AppContainer.carRepository

    var cars = mutableStateOf<List<CarDto>>(emptyList())
        private set

    init {
        loadCars()
    }

    fun loadCars() {
        viewModelScope.launch {
            try {
                cars.value = repository.getCars()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveCar(carId: Long) {
        viewModelScope.launch {
            try {
                repository.saveCar(carId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}