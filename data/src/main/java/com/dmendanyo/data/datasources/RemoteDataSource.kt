package com.dmendanyo.data.datasources

import com.dmendanyo.domain.models.Movie

interface RemoteDataSource {

    suspend fun getMovies(region: String): Result<List<Movie>>

    suspend fun search(query: String): Result<List<Movie>>

    suspend fun getMovieById(id: Int): Result<Movie>
}
