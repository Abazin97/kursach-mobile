package com.example.kursach.data.remote.dto

data class CarDto(
    val id: Long,
    val brand: String,
    val model: String,
    val year: Int,
    val price: Long,
    val description: String
)