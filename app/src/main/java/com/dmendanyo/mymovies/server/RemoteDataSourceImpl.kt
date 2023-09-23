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
