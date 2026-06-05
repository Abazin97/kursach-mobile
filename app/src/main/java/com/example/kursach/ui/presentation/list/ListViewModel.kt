package com.example.kursach.ui.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.di.AppContainer
import com.example.kursach.domain.repository.CarRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: CarRepository = AppContainer.carRepository
) : ViewModel() {

    var cars = mutableStateOf<List<CarDto>>(emptyList())
        private set

    var loading = mutableStateOf(false)
        private set

    fun loadSavedCars() {
        viewModelScope.launch {
            loading.value = true

            try {
                cars.value = repository.getSavedCars()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            loading.value = false
        }
    }

    fun removeCar(carId: Long) {
        viewModelScope.launch {
            repository.removeCar(carId)
            loadSavedCars()
        }
    }
}