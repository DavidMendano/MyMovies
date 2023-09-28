package com.dmendanyo.domain.repositories

import com.dmendanyo.domain.models.Error
import com.dmendanyo.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MyMoviesRepository {

    val movies: Flow<List<Movie>>

    val favorites: Flow<List<Movie>>

    suspend fun getMovies(): Error?

    suspend fun switchLike(id: Int)

    suspend fun getMovieDetail(id: Int): Flow<Movie?>

    suspend fun search(query: String): Flow<List<Movie>>

    suspend fun getMovieDetailFromServer(id: Int): Flow<Movie?>
}