package com.dmendanyo.data.datasources

interface LocationDataSource {

    suspend fun getLastKnownRegion(): String?
}