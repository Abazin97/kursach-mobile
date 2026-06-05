package com.example.kursach.data.remote.api

import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.data.remote.dto.request.DeleteCarRequest
import com.example.kursach.data.remote.dto.request.SaveCarRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface CarApi {

    @GET("cars")
    suspend fun getCars(): List<CarDto>

    @POST("cars/save")
    suspend fun saveCar(
        @Body request: SaveCarRequest
    )
    @GET("cars/saved")
    suspend fun getSavedCars(): List<CarDto>

    @HTTP(method = "DELETE", path = "cars/remove", hasBody = true)
    suspend fun removeCar(@Body request: DeleteCarRequest)
}