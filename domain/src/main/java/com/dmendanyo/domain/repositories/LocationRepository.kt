package com.dmendanyo.domain.repositories

interface LocationRepository {

    suspend fun getRegion(): String
}