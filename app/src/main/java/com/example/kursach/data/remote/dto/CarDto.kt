package com.example.kursach.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarDto(
    val id: Long,
    val brand: String,
    val model: String,
    val year: Int,
    val price: Long,
    val description: String
) : Parcelable