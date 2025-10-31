package com.example.weatherforecast.services

import com.example.weatherforecast.models.Weather
import com.example.weatherforecast.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") cityOrLatLon: String,
        @Query("days") days: Int = 3
    ): Weather
}
