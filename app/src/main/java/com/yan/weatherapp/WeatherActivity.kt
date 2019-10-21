package com.yan.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.yan.weatherapp.api.repositories.WeatherRepository
import com.yan.weatherapp.api.services.WeatherService
import com.yan.weatherapp.data.GeoProvider
import com.yan.weatherapp.heplers.GeoPermissionManager
import com.yan.weatherapp.models.Weather
import com.yan.weatherapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class WeatherActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupViews()
        connectViewModel()
        handleGeolocationPermission()
    }

    private fun handleGeolocationPermission() {
        if (!GeoPermissionManager.isPermissionGranted(this)) {
            GeoPermissionManager.requestGeoPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>, grantResults: IntArray) {

        if (GeoPermissionManager.didUserGrantedPermission(requestCode, grantResults)) {
            Snackbar.make(fab, "Permission has been granted by user", Snackbar.LENGTH_LONG)
                    .show()
            viewModel.refresh()

        } else {
            Snackbar.make(fab, "Permission has been denied by user", Snackbar.LENGTH_LONG)
                    .show()
        }
    }

    private fun setupViews() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Refreshing weather info", Snackbar.LENGTH_SHORT).show()
            viewModel.refresh()
        }
    }

    private fun connectViewModel() {
        viewModel = createViewModel()
        viewModel.getWeather().observe(this, Observer<Weather> { weather ->
            timeZoneTextView.text = weather.timezone
            temperatureValueTextView.text = weather.currently.temperature.toString()
            imageView.setImageResource(getImageResource(weather.currently.icon))
        })

        viewModel.getLoadError().observe(this, Observer<String> { errorMsg ->
            Snackbar.make(fab, errorMsg, Snackbar.LENGTH_LONG).show()
        })
    }

    private fun getImageResource(icon: String): Int = when (icon) {
        "clear-day" -> R.drawable.ic_clear_day
        "clear-night" -> R.drawable.ic_clear_night
        "rain" -> R.drawable.ic_rain
        "snow" -> R.drawable.ic_snow
        "wind" -> R.drawable.ic_wind
        "fog" -> R.drawable.ic_fog
        "cloudy" -> R.drawable.ic_cloudy
        "partly-cloudy-day" -> R.drawable.ic_partly_cloudy_day
        " partly-cloudy-night" -> R.drawable.ic_partly_cloudy_night
        else -> R.drawable.ic_default
    }

    //TODO : Inject with Koin
    private fun createViewModel() =
            WeatherViewModel(WeatherRepository(WeatherService.create()), GeoProvider())

}