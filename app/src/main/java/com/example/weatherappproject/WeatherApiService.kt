package com.example.weatherappproject // ⚠️ 确保包名一致

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    // ⚠️ 请将下面的 YOUR_API_KEY_HERE 替换为你自己的 OpenWeatherMap API Key！
    @GET("data/2.5/weather?appid=e235c3c51ada2fdab61de10b036c4d3b&units=metric&lang=ru")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String
    ): WeatherResponse
}