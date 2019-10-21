package com.yan.weatherapp.api.repositories

import com.yan.weatherapp.api.services.WeatherService
import com.yan.weatherapp.models.Weather
import io.reactivex.Single

class WeatherRepository(private val weatherService: WeatherService) {

    fun getWeather(longitude: Double, latitude: Double): Single<Weather> =
            weatherService.getWeather(longitude, latitude)

}