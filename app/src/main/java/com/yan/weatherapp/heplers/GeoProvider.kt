package com.yan.weatherapp.heplers

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.yan.weatherapp.App

class GeoProvider(private val appContext: Context = App.appContext) : LocationListener {

    private var listener: Listener? = null

    interface Listener {
        fun onLocationReady(longitude: Double, latitude: Double)
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            stopLocationsUpdate()
            listener?.onLocationReady(it.longitude, it.latitude)
            this.listener = null
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    @SuppressLint("MissingPermission")
    fun updateLocation(listener: Listener? = null) {
        this.listener = listener
        val lm = appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        lm?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, this)
    }

    private fun stopLocationsUpdate() {
        val lm = appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        lm?.removeUpdates(this)
    }
}