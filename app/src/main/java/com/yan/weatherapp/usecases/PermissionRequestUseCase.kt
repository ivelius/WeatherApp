package com.yan.weatherapp.usecases

import com.yan.weatherapp.WeatherActivity
import com.yan.weatherapp.heplers.GeoPermissionManager
import com.yan.weatherapp.viewmodel.WeatherViewModel

class PermissionRequestUseCase(
        private val
        weatherActivity: WeatherActivity,
        private val viewModel: WeatherViewModel) {

    fun execute() {
        handleGeolocationPermission()
    }

    private fun handleGeolocationPermission() {
        if (!GeoPermissionManager.isPermissionGranted(weatherActivity)) {
            GeoPermissionManager.requestGeoPermission(weatherActivity)
        } else {
            viewModel.refresh()
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (GeoPermissionManager.didUserGrantedPermission(requestCode, grantResults)) {
            viewModel.refresh()
        } else {
            viewModel.permissionError.value = "Permission has been denied by user"
        }

    }
}