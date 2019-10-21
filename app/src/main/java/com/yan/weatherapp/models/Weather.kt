package com.yan.weatherapp.models

import com.yan.weatherapp.api.services.models.*

data class Weather(
    val alerts: List<Alert>,
    val currently: Currently,
    val daily: Daily,
    val flags: Flags,
    val hourly: Hourly,
    val latitude: Double,
    val longitude: Double,
    val offset: Int,
    val timezone: String
)