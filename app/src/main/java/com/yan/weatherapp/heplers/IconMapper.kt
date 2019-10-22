package com.yan.weatherapp.heplers

import com.yan.weatherapp.R

class IconMapper {

    companion object {
        fun getImageResource(icon: String): Int = when (icon) {
            "clear-day" -> R.drawable.ic_clear_day
            "clear-night" -> R.drawable.ic_clear_night
            "rain" -> R.drawable.ic_rain
            "snow" -> R.drawable.ic_snow
            "wind" -> R.drawable.ic_wind
            "fog" -> R.drawable.ic_fog
            "cloudy" -> R.drawable.ic_cloudy
            "partly-cloudy-day" -> R.drawable.ic_partly_cloudy_day
            "partly-cloudy-night" -> R.drawable.ic_partly_cloudy_night
            else -> R.drawable.ic_default
        }

    }
}