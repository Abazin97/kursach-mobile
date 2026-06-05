package com.example.kursach.ui.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class SavedCarsViewModel : ViewModel() {

    private val savedCars = mutableStateListOf<Long>()

    fun isSaved(carId: Long): Boolean {
        return carId in savedCars
    }

    fun addCar(carId: Long) {
        if (!savedCars.contains(carId)) {
            savedCars.add(carId)
        }
    }
}