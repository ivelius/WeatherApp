package com.yan.weatherapp.api.services.models

import com.yan.weatherapp.models.DailyData

data class Daily(
    val `data`: List<DailyData>,
    val icon: String,
    val summary: String
)