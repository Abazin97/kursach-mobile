package com.example.kursach.domain.repository

import com.example.kursach.data.remote.dto.CarDto

interface CarRepository {

    suspend fun getCars(): List<CarDto>

    suspend fun saveCar(carId: Long)
}