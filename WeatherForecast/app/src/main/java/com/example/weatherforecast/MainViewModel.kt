package com.example.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.models.ForecastDayWrapper
import com.example.weatherforecast.models.Weather
import com.example.weatherforecast.services.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    fun fetchWeather(cityOrLatLon: String) {
        viewModelScope.launch {
            try {
                val response = weatherService.getForecast(cityOrLatLon = cityOrLatLon)
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}