package com.dmendanyo.mymovies.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyMoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String,
    ): ResultModel

    @GET("search/movie")
    suspend fun search(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
    ): ResultModel

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): ResultMovieModel
}
