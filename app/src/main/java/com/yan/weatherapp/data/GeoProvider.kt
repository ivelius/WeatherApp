package com.yan.weatherapp.data

import android.content.Context
import android.location.LocationManager

class GeoProvider() {
    internal val longitude: Double get() = 59.337239
    internal val latitude: Double get() = 18.062381

//    constructor(ctx: Context) {
//        val lm = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
//        val location = lm!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        val longitude = location.longitude
//        val latitude = location.latitude
//    }
}