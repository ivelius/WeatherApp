package com.yan.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.yan.utils.RxUtils
import com.yan.weatherapp.api.repositories.WeatherRepository
import com.yan.weatherapp.api.services.WeatherService
import com.yan.weatherapp.heplers.GeoProvider
import com.yan.weatherapp.models.Weather
import com.yan.weatherapp.viewmodel.WeatherViewModel
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewModelUnitTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var geoProvider: GeoProvider
    private lateinit var sut: WeatherViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        //Make fresh instances for every test
        weatherRepository = mock(WeatherRepository::class.java)
        geoProvider = mock(GeoProvider::class.java)
        sut = WeatherViewModel(weatherRepository, geoProvider)

        RxUtils.makeRxSchedulersImmediate()
    }

    @After
    fun tearDown() {
        RxUtils.resetRxSchedulers()
    }

    @Test
    fun data_change_test() {

        //Given
        //weather service provides data
        val weather = mock(Weather::class.java)
        `when`(
            weatherRepository.getWeather(
                any(Double::class.java),
                any(Double::class.java))).thenReturn(Single.just(weather))

        //When location is available
        sut.onLocationReady(-1.0, 1.0)

        //View model value live data is the same as weather repository returns
        assertEquals(weather, sut.getWeather().value)
    }

}
