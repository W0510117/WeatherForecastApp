package com.example.weatherforecast.models

data class Current(
    val location: String,
    val temperature: Double,
    val condition: String,
    val humidity: Int,
    val windSpeed: Double
)

data class Forecast(
    val day: String,
    val temperature: Double,
    val condition: String
)

data class Weather(
    val current: Current,
    val dailyForecast: List<Forecast>
)
