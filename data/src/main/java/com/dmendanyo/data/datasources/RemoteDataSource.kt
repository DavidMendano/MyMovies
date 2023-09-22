package com.dmendanyo.data.datasources

import com.dmendanyo.domain.models.MovieApiModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getMovies(): Result<List<MovieApiModel>>
}
