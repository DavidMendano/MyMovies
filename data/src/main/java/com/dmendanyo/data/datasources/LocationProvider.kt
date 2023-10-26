package com.dmendanyo.data.datasources

interface LocationProvider {

    suspend fun getRegion(): String
}