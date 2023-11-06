package com.dmendanyo.data

interface PermissionChecker {

    enum class Permission { ACCESS_COARSE_LOCATION }

    fun check(permission: Permission): Boolean
}