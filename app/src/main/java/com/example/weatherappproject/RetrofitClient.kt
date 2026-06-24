package com.example.weatherappproject // ⚠️ 确保包名一致

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.openweathermap.org/"

    val apiService: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析 JSON
            .build()
            .create(WeatherApiService::class.java)
    }
}