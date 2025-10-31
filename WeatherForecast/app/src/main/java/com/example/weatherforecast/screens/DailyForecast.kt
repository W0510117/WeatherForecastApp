package com.example.weatherforecast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.models.ForecastDayWrapper

@Composable
fun DailyForecast(forecasts: List<ForecastDayWrapper>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(forecasts) { wrapper ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = wrapper.date, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Max: ${wrapper.day.maxtemp_c}°C, Min: ${wrapper.day.mintemp_c}°C"
                    )
                    Text(text = "Condition: ${wrapper.day.condition.text}")
                    Text(text = wrapper.day.description)
                }
            }
        }
    }
}

