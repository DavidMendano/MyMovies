package com.dmendanyo.mymovies

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.dmendanyo.data.PermissionChecker
import javax.inject.Inject

class AndroidPermissionChecker @Inject constructor(
    private val application: Application,
) : PermissionChecker {

    override fun check(permission: PermissionChecker.Permission): Boolean =
        ContextCompat.checkSelfPermission(
            application,
            permission.toAndroidPermission()
        ) == PackageManager.PERMISSION_GRANTED
}

fun PermissionChecker.Permission.toAndroidPermission(): String =
    when (this) {
        PermissionChecker.Permission.ACCESS_COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
    }