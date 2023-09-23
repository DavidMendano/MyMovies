package com.dmendanyo.data.datasources

import com.dmendanyo.domain.models.Movie

interface RemoteDataSource {

    suspend fun getMovies(): Result<List<Movie>>
}
