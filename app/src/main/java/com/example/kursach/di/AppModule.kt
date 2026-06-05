package com.example.kursach.di

import android.content.Context
import com.example.kursach.data.local.JwtStorage
import com.example.kursach.data.local.dataStore
import com.example.kursach.data.remote.AuthInterceptor
import com.example.kursach.data.remote.api.AuthApi
import com.example.kursach.data.remote.api.CarApi
import com.example.kursach.data.repository.AuthImpl
import com.example.kursach.data.repository.CarRepositoryImpl
import com.example.kursach.domain.repository.AuthRepository
import com.example.kursach.domain.repository.CarRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppContainer {

    private lateinit var appContext: Context

    fun init(context: Context) {

        val dataStore = context.applicationContext.dataStore

        jwtStorage = JwtStorage(dataStore)
    }

    lateinit var jwtStorage: JwtStorage
        private set

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor { jwtStorage.getSessionSync()?.token })
            .addInterceptor(logging)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val authRepository: AuthRepository by lazy {
        AuthImpl(authApi, jwtStorage)
    }

    val carApi: CarApi by lazy {
        retrofit.create(CarApi::class.java)
    }

    val carRepository: CarRepository by lazy {
        CarRepositoryImpl(carApi)
    }
}