package com.dmendanyo.data.repositories

import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.domain.models.MovieApiModel
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MyMoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MyMoviesRepository {

    override suspend fun getMovies(): Flow<List<MovieApiModel>> =
        flowOf(remoteDataSource.getMovies().getOrDefault(listOf()))
}
