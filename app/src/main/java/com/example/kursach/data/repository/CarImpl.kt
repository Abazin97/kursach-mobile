package com.example.kursach.data.repository

import com.example.kursach.data.remote.api.CarApi
import com.example.kursach.domain.repository.CarRepository
import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.data.remote.dto.request.SaveCarRequest

class CarRepositoryImpl(
    private val api: CarApi
) : CarRepository {

    override suspend fun getCars(): List<CarDto> {
        return api.getCars()
    }

    override suspend fun saveCar(carId: Long) {
        api.saveCar(
            SaveCarRequest(carId)
        )
    }
}