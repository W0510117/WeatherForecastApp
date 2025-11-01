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

//New class to get condition
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

//Updated
data class Current(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    val condition: Condition,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    val humidity: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
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

//Updated
data class ForecastDay(
    val date: String,
    val date_epoch: Long,
    val day: DayInfo
)

data class DayInfo(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val avgtemp_c: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    val totalprecip_mm: Double,
    val avghumidity: Double,
    val daily_will_it_rain: Int,
    val daily_chance_of_rain: Int,
    val daily_will_it_snow: Int,
    val daily_chance_of_snow: Int,
    val condition: Condition,
    val uv: Double
)

data class ForecastDayWrapper(
    val date: String,
    val dayInfo: DayInfo
)
