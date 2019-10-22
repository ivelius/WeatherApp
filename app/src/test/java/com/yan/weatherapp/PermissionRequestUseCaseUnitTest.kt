package com.yan.weatherapp

import com.yan.weatherapp.heplers.GeoPermissionManager
import com.yan.weatherapp.usecases.PermissionRequestUseCase
import com.yan.weatherapp.viewmodel.WeatherViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PermissionRequestUseCaseUnitTest {

    private lateinit var weatherActivity: WeatherActivity
    private lateinit var viewModel: WeatherViewModel
    private lateinit var geoPermissionManager: GeoPermissionManager
    private lateinit var sut: PermissionRequestUseCase

    @Before
    fun setup() {
        //Make fresh instances for every test
        weatherActivity = mock(WeatherActivity::class.java)
        viewModel = mock(WeatherViewModel::class.java)
        geoPermissionManager = mock(GeoPermissionManager::class.java)
        sut = PermissionRequestUseCase(weatherActivity, viewModel, geoPermissionManager)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun permission_granted_test() {

        //Given
        //Permission is granted
        `when`(geoPermissionManager.isPermissionGranted(weatherActivity)).thenReturn(true)

        //When use case is executed
        sut.execute()

        //View model will refresh the data
        verify(viewModel, times(1)).refresh()
    }

    @Test
    fun permission_not_granted_test() {
        //Given
        //Permission is not granted
        `when`(geoPermissionManager.isPermissionGranted(weatherActivity)).thenReturn(false)

        //When use case is executed
        sut.execute()

        //Geolocation permissions will be requested
        verify(geoPermissionManager, times(1)).requestGeoPermission(weatherActivity)
    }
}
