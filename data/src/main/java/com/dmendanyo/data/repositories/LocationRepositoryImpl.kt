package com.dmendanyo.data.repositories

import com.dmendanyo.data.PermissionChecker
import com.dmendanyo.data.datasources.LocationDataSource
import com.dmendanyo.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker,
) : LocationRepository {

    companion object {
        const val defaultRegion = "US"
    }

    override suspend fun getRegion(): String =
        if (permissionChecker.check(PermissionChecker.Permission.ACCESS_COARSE_LOCATION)) {
            locationDataSource.getLastKnownRegion() ?: defaultRegion
        } else {
            defaultRegion
        }
}