package com.yan.weatherapp.api.services.models

data class Alert(
    val description: String,
    val expires: Int,
    val regions: List<String>,
    val severity: String,
    val time: Int,
    val title: String,
    val uri: String
)