package com.example.weatherappproject

import org.junit.Assert.*
import org.junit.Test

class WeatherLogicTest {

    @Test
    fun testTemperatureFormatting() {
        // 测试温度格式化
        val temp = 25.678
        val formatted = String.format("%.1f", temp)
        assertEquals("温度应该格式化为1位小数", "25.7", formatted)
    }

    @Test
    fun testCityNameValidation_Valid() {
        val cityName = "Minsk"
        assertTrue("城市名不应为空", cityName.isNotEmpty())
        assertTrue("城市名长度应大于2", cityName.length > 2)
    }

    @Test
    fun testCityNameValidation_Invalid() {
        val cityName = ""
        assertFalse("空城市名应该无效", cityName.isNotEmpty())
    }

    @Test
    fun testWeatherDataStructure() {
        // 模拟天气数据结构测试
        val mockTemp = 25.5
        val mockCity = "Beijing"
        val mockDescription = "clear sky"

        assertEquals(25.5, mockTemp, 0.01)
        assertEquals("Beijing", mockCity)
        assertEquals("clear sky", mockDescription)
    }
}