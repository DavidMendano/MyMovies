package com.dmendanyo.mymovies.database

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.core.content.ContextCompat
import com.dmendanyo.data.datasources.LocationProvider
import com.dmendanyo.mymovies.ui.common.defaultRegion
import com.dmendanyo.mymovies.ui.common.locationPermission
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationProviderImpl @Inject constructor(
    private val application: Application
) : LocationProvider {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)

    override suspend fun getRegion(): String =
        if (
            ContextCompat.checkSelfPermission(
                application,
                locationPermission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLastKnownLocation().toRegion() ?: defaultRegion
        } else {
            defaultRegion
        }

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
