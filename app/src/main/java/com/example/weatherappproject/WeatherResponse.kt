package com.example.weatherappproject // ⚠️ 确保包名一致

// OpenWeatherMap 返回的根对象
data class WeatherResponse(
    val name: String,           // 城市名
    val main: Main,             // 温度等主数据
    val weather: List<Weather>  // 天气描述和图标
)

data class Main(
    val temp: Double,           // 当前温度
    val humidity: Int           // 湿度
)

data class Weather(
    val description: String,    // 天气描述 (如 "ясно")
    val icon: String            // 图标代码 (如 "01d")
)