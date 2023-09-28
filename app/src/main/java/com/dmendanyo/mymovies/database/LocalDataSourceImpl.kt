package com.dmendanyo.mymovies.database

import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val moviesDao: MyMoviesDao,
) : LocalDataSource {

    override fun getAllMovies(): Flow<List<Movie>> =
        moviesDao.getAllMovies().map { it.toDomainModel() }

    override fun getFavorites(): Flow<List<Movie>> =
        moviesDao.getFavorites().map { it.toDomainModel() }

    override suspend fun saveMovies(movies: List<Movie>) =
        moviesDao.insertMovies(movies.fromDomainModel())

    override suspend fun switchLike(id: Int) {
        moviesDao.findById(id)
            .take(1)
            .filterNotNull()
            .collect {
                val movie = it.copy(favorite = !it.favorite)
                moviesDao.insertMovie(movie)
            }
    }

    override suspend fun isEmpty(): Boolean = moviesDao.moviesCount() == 0

    override suspend fun getMovieDetail(id: Int): Flow<Movie?> =
        moviesDao.findById(id)
            .map { it?.toDomainModel() }
}
