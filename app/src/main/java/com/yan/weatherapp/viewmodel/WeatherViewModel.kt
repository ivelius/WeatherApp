package com.yan.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yan.weatherapp.api.repositories.WeatherRepository
import com.yan.weatherapp.heplers.GeoProvider
import com.yan.weatherapp.models.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(
        private val weatherRepository: WeatherRepository,
        private val geoProvider: GeoProvider) : ViewModel() {

    private val disposablesBag: CompositeDisposable = CompositeDisposable()
    private val weather: MutableLiveData<Weather> = MutableLiveData()
    private val loadError: MutableLiveData<String> = MutableLiveData()

    fun getWeather(): LiveData<Weather> {
        return weather
    }

    fun getLoadError(): LiveData<String> {
        return loadError
    }

    override fun onCleared() {
        super.onCleared()
        disposablesBag.dispose()
    }

    fun refresh() {
        geoProvider.updateLocation(listener = object : GeoProvider.Listener {
            override fun onLocationReady(longitude: Double, latitude: Double) {
                disposablesBag.add(
                    weatherRepository.getWeather(longitude, latitude)
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                { result ->
                                    weather.value = result
                                },
                                { error ->
                                    loadError.value = error.message
                                }
                            )
                )
            }
        })
    }
}