package com.example.weatherapp

data class WeatherResponse(
    val name: String,
    val main: MainWeather,
    val weather: List<WeatherInfo>
)

data class MainWeather(
    val temp: Double
)

data class WeatherInfo(
    val main: String,
    val description: String,
    val icon: String
)
