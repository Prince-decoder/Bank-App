package com.bank.locationapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import com.google.android.gms.location.LocationRequest
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationUtils(val context: Context) {
    private val _fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel)
    {
        val locationcallback=object : LocationCallback()
        {
            override fun onLocationResult(locationresult: LocationResult) {
                super.onLocationResult(locationresult)
                locationresult.lastLocation?.let {
                    val location = LocationData(latitude = it.latitude, longitude = it.longitude )
                    viewModel.updateLocation(location)

                }
            }
        }
        val locationRequest= LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,1000).build()
        _fusedLocationClient.requestLocationUpdates(locationRequest,locationcallback, Looper.getMainLooper())
    }
    fun hasLocation(context: Context): Boolean
    {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        )
        {
            return true
        }
        else
        {
            return false
        }
    }
}