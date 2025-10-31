package com.example.weatherforecast.models

import com.google.gson.annotations.SerializedName

//New class to get location
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Int,
    val localtime: String
)

//Updated
data class Current(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    @SerializedName("feelslike_c") val feelsLikeC: Double
)

//New class to get condition
data class Condition(
    val text: String,
    val icon: String
)



//Updated
data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

//added for multiple days
data class Forecast(
    val forecastday: List<ForecastDay>
)

// Wrapper
data class ForecastDayWrapper(
    val date: String,
    val day: ForecastDay
)

//Updated
data class ForecastDay(
    val date: String, // <-- this is the date string
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val avgtemp_c: Double,
    val maxwind_kph: Double,
    val condition: Condition,
    val description: String
)
