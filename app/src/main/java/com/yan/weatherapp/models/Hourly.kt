package com.yan.weatherapp.models

import com.yan.weatherapp.api.services.models.HourlyData

data class Hourly(
    val `data`: List<HourlyData>,
    val icon: String,
    val summary: String
)