package com.example.kursach.data.repository

import com.example.kursach.data.remote.api.CarApi
import com.example.kursach.domain.repository.CarRepository
import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.data.remote.dto.request.DeleteCarRequest
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

    override suspend fun getSavedCars(): List<CarDto> {
        return api.getSavedCars()
    }

    override suspend fun removeCar(carId: Long) {
        api.removeCar(DeleteCarRequest(carId))
    }
}