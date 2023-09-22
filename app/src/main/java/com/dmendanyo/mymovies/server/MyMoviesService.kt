package com.dmendanyo.mymovies.server

import com.dmendanyo.domain.models.MovieApiResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MyMoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
    ): MovieApiResultModel
}