package com.dmendanyo.data.repositories

import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.domain.extensions.mapToError
import com.dmendanyo.domain.models.Error
import com.dmendanyo.domain.models.Movie
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyMoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MyMoviesRepository {

    override val movies: Flow<List<Movie>>
        get() = localDataSource.getAllMovies()

    override suspend fun getMovies(): Error? =
        remoteDataSource.getMovies()
            .fold(
                onSuccess = {
                    localDataSource.saveMovies(it)
                    null
                },
                onFailure = { it.mapToError() },
            )
}
