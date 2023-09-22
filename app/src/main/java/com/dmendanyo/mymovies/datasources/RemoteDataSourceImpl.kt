package com.dmendanyo.mymovies.datasources

import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.domain.models.MovieApiModel
import com.dmendanyo.mymovies.di.ApiKey
import com.dmendanyo.mymovies.server.MyMoviesService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    @ApiKey private val apiKey: String,
    private val service: MyMoviesService,
): RemoteDataSource {

    override suspend fun getMovies(): Result<List<MovieApiModel>> =
        safeCall {
            service.getPopularMovies(apiKey, "es").results
        }
}

suspend fun <T> safeCall(block: suspend () -> T): Result<T> =
    try {
        val result = block.invoke()
        Result.success(result)
    } catch (exception: Exception) {
        Result.failure(exception)
    }
