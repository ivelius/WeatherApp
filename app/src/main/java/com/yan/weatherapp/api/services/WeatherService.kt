package com.yan.weatherapp.api.services

import com.yan.weatherapp.BuildConfig
import com.yan.weatherapp.models.Weather
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("/forecast/${BuildConfig.API_KEY}/{longitude},{latitude}")
    fun getWeather(
            @Path("longitude") longitude: Double,
            @Path("latitude") latitude: Double): Single<Weather>

    companion object {

        private val BASE_URL = "https://api.darksky.net/"

        fun create(): WeatherService {
            val httpClient = createHttpClient()
            val retrofit = Retrofit.Builder()
                    .client(httpClient.build())
                    .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                        GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(WeatherService::class.java)
        }

        private fun createHttpClient(): OkHttpClient.Builder {
            val httpClient = OkHttpClient.Builder()
            val logging =
                    createLoggingInterceptor()
            // add logging as last interceptor for better debugging
            httpClient.addInterceptor(logging)
            return httpClient
        }

        private fun createLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging
        }
    }
}

