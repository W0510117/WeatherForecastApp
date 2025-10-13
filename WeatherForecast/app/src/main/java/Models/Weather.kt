package com.example.weatherforecast.models

data class Current(
    val location: String,
    val temperature: Double,
    val condition: String,
    val humidity: Int,
    val windSpeed: Double,
    val description: String,
    val emoji: String,
    val feels: Double
)

data class Forecast(
    val day: String,
    val hightemperature: Double,
    val lowtemperature: Double,
    val condition: String,
    val emoji: String,
    val description: String
)

data class Weather(
    val current: Current,
    val dailyForecast: List<Forecast>
)
