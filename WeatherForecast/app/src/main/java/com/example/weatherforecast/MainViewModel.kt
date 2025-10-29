package com.example.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.models.Current
import com.example.weatherforecast.models.Forecast
import com.example.weatherforecast.models.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null) //mutable StateFlow for weather data
    val weather = _weather.asStateFlow() //the UI (screens) can only read/collect it

    init {
        viewModelScope.launch {
            val currentWeather = Current(
                location = "Halifax",
                temperature = 12.0,
                condition = "Sunny",
                humidity = 100,
                windSpeed = 200.0,
                description = "Clear skies.",
                emoji = "☀️",
                feels = 12.0
            )

            val forecastList = listOf(
                Forecast(
                    day = "Mon",
                    hightemperature = 30.0,
                    lowtemperature = 20.0,
                    condition = "Sunny",
                    emoji = "☀️",
                    description = "Clear skies throughout the day, with a humidity of 80%, and a wind speed of 10 km/h."
                ),
                Forecast(
                    day = "Tue",
                    hightemperature = 28.0,
                    lowtemperature = 18.0,
                    condition = "Cloudy",
                    emoji = "☁️",
                    description = "Partly cloudy with mild breeze, with a humidity of 60%, and a wind speed of 10 km/h."
                ),
                Forecast(
                    day = "Wed",
                    hightemperature = 15.0,
                    lowtemperature = 10.0,
                    condition = "Rainy",
                    emoji = "🌧️",
                    description = "Expect showers with a chance of thunderstorms throughout the day, with a humidity of 70%, and a wind speed of 15 km/h."
                )
            )

            _weather.value = Weather(current = currentWeather, dailyForecast = forecastList)
        }
    }
}
