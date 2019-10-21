package com.yan.weatherapp

import com.yan.weatherapp.api.repositories.WeatherRepository
import com.yan.weatherapp.heplers.GeoProvider
import com.yan.weatherapp.models.Weather
import com.yan.weatherapp.viewmodel.WeatherViewModel
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WeatherViewModelUnitTest {

    private val weatherRepository: WeatherRepository = mock(WeatherRepository::class.java)
    private val geoProvider: GeoProvider = mock(GeoProvider::class.java)
    val sut = WeatherViewModel(weatherRepository, geoProvider)

    @Before
    fun setup() {
        `when`(geoProvider.latitude).thenReturn(0.1)
        `when`(geoProvider.longitude).thenReturn(-0.1)
        `when`(weatherRepository.getWeather(geoProvider.latitude, geoProvider.longitude))
                .thenReturn(Single.just(mock(Weather::class.java)))
    }

    @After
    fun tearDown() {

    }

    @Test
    fun refresh_called() {
        sut.refresh()
        verify(weatherRepository, times(1))
                .getWeather(geoProvider.latitude, geoProvider.longitude)
    }
}
