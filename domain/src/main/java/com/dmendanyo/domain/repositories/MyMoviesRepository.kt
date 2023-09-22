package com.dmendanyo.domain.repositories

import com.dmendanyo.domain.models.MovieApiModel
import kotlinx.coroutines.flow.Flow

interface MyMoviesRepository {

    suspend fun getMovies(): Flow<List<MovieApiModel>>
}