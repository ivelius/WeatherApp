package com.yan.weatherapp.api.services.models

import com.google.gson.annotations.SerializedName

data class Flags(
        @SerializedName("meteoalarm-license") val meteoalarmLicense: String,
        @SerializedName("nearest-station") val nearestStation: Double,
        val sources: List<String>,
        val units: String
)