package com.example.weatherforecast

import androidx.lifecycle.ViewModel
import com.example.weatherforecast.models.Current
import com.example.weatherforecast.models.Forecast
import com.example.weatherforecast.models.Weather

class MainViewModel : ViewModel() {

    val weather: Weather

    init {
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
            Forecast("Mon", 30.0, 20.0, "Sunny", "☀️", "Clear skies throughout the day, with a humidity of 80%, and a wind speed of 10 km/h."),
            Forecast("Tue", 28.0, 18.0, "Cloudy", "☁️", "Partly cloudy with mild breeze, with a humidity of 60%, and a wind speed of 10 km/h."),
            Forecast("Wed", 15.0, 10.0, "Rainy", "🌧️", "Expect showers with a chance of thunderstorms after throughout the day, with a humidity of 70%, and a wind speed of 15 km/h."),
        )

        weather = Weather(
            current = currentWeather,
            dailyForecast = forecastList
        )
    }
}
