package com.example.kursach.data.remote.api

import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.data.remote.dto.request.SaveCarRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarApi {

    @GET("cars")
    suspend fun getCars(): List<CarDto>

    @POST("cars/save")
    suspend fun saveCar(
        @Body request: SaveCarRequest
    )
}