package com.dmendanyo.domain.repositories

import com.dmendanyo.domain.models.Error
import com.dmendanyo.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MyMoviesRepository {

    val movies: Flow<List<Movie>>

    suspend fun getMovies(): Error?
}