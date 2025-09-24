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
            condition = "☀️ Sunny",
            humidity = 100,
            windSpeed = 200.0
        )

        val forecastList = listOf(
            Forecast("Monday", 30.0, "☀️"),
            Forecast("Tuesday", 15.0, "🌤"),
            Forecast("Wednesday", 10.0, "🌧"),
            Forecast("Thursday", 12.0, "⛅"),
            Forecast("Friday", 14.0, "🌦"),
            Forecast("Saturday", 11.0, "☁️"),
            Forecast("Sunday", 5.0, "🌨")
        )

        weather = Weather(
            current = currentWeather,
            dailyForecast = forecastList
        )
    }
}
