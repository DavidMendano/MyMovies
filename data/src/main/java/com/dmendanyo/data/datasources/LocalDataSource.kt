package com.dmendanyo.data.datasources

import com.dmendanyo.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllMovies(): Flow<List<Movie>>

    suspend fun saveMovies(movies: List<Movie>)
}