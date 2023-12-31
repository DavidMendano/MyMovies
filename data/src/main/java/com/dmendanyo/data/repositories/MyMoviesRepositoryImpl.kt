package com.dmendanyo.data.repositories

import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.domain.extensions.mapToError
import com.dmendanyo.domain.models.Error
import com.dmendanyo.domain.models.Movie
import com.dmendanyo.domain.repositories.LocationRepository
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MyMoviesRepositoryImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MyMoviesRepository {

    override val movies: Flow<List<Movie>>
        get() = localDataSource.getAllMovies()

    override val favorites: Flow<List<Movie>>
        get() = localDataSource.getFavorites()

    override suspend fun getMovies(): Error? =
        if (localDataSource.isEmpty()) {
            remoteDataSource.getMovies(locationRepository.getRegion())
                .fold(
                    onSuccess = {
                        localDataSource.saveMovies(it)
                        null
                    },
                    onFailure = { it.mapToError() },
                )
        } else {
            null
        }

    override suspend fun switchLike(id: Int) {
        localDataSource.switchLike(id)
    }

    override suspend fun getMovieDetail(id: Int): Flow<Movie?> =
        localDataSource.getMovieDetail(id)

    override suspend fun search(query: String): Flow<List<Movie>> =
        remoteDataSource.search(query)
            .fold(
                onSuccess = { flowOf(it) },
                onFailure = { flowOf(listOf()) },
            )

    override suspend fun getMovieDetailFromServer(id: Int): Flow<Movie?> =
        flowOf(remoteDataSource.getMovieById(id).getOrNull())
}
