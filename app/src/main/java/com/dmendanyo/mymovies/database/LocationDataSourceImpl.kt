package com.dmendanyo.mymovies.database

import android.annotation.SuppressLint
import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.dmendanyo.data.datasources.LocationDataSource
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationDataSourceImpl @Inject constructor(application: Application) : LocationDataSource {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)

    override suspend fun getLastKnownRegion(): String? = getLastKnownLocation().toRegion()

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    private suspend fun getLastKnownLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result, null)
                }
        }

    private suspend fun Location?.toRegion(): String? =
        this?.let { geocoder.getFromLocation(it).firstOrNull()?.countryName }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Suppress("DEPRECATION")
    private suspend fun Geocoder.getFromLocation(location: Location): List<Address> =
        suspendCancellableCoroutine { continuation ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getFromLocation(location.latitude, location.longitude, 1) {
                    continuation.resume(it, null)
                }
            } else {
                val addresses = getFromLocation(location.latitude, location.longitude, 1)
                continuation.resume(addresses ?: emptyList(), null)
            }
        }
}
