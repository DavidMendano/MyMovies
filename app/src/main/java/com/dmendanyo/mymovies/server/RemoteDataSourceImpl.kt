package com.dmendanyo.mymovies.server

import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.domain.extensions.safeCall
import com.dmendanyo.domain.models.Movie
import com.dmendanyo.mymovies.di.ApiKey
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    @ApiKey private val apiKey: String,
    private val service: MyMoviesService,
) : RemoteDataSource {

    override suspend fun getMovies(): Result<List<Movie>> =
        safeCall {
            service
                .getPopularMovies(apiKey, "es")
                .results
                .map { it.toDomainModel() }
        }

    override suspend fun search(query: String): Result<List<Movie>> =
        safeCall {
            service
                .search(apiKey, query)
                .results
                .map { it.toDomainModel() }
        }

    override suspend fun getMovieById(id: Int): Result<Movie> =
        safeCall {
            service
                .getMovieById(id, apiKey)
                .toDomainModel()
        }
}

private fun ResultMovieModel.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        urlImage = urlImage,
        backdropPath = backdropPath ?: "",
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        favorite = false,
    )
