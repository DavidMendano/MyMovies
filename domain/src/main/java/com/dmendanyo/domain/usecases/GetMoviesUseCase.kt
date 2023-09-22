package com.dmendanyo.domain.usecases

import com.dmendanyo.domain.models.MovieApiModel
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MyMoviesRepository
) {

    suspend fun invoke(): Flow<List<MovieApiModel>> =
        repository.getMovies()
}
