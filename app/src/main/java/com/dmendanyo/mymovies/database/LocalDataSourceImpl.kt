package com.dmendanyo.mymovies.database

import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val moviesDao: MyMoviesDao,
) : LocalDataSource {

    override fun getAllMovies(): Flow<List<Movie>> =
        moviesDao.getAllMovies().map { it.toDomainModel() }

    override suspend fun saveMovies(movies: List<Movie>) =
        moviesDao.insertMovies(movies.fromDomainModel())
}
