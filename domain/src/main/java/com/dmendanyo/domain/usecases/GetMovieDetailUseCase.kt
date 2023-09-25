package com.dmendanyo.domain.usecases

import com.dmendanyo.domain.models.Movie
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MyMoviesRepository,
) {

    suspend operator fun invoke(id: Int): Flow<Movie> =
        repository.getMovieDetail(id)
}