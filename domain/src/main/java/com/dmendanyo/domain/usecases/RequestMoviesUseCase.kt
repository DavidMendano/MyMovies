package com.dmendanyo.domain.usecases

import com.dmendanyo.domain.models.Error
import com.dmendanyo.domain.repositories.MyMoviesRepository
import javax.inject.Inject

class RequestMoviesUseCase @Inject constructor(
    private val remoteRepository: MyMoviesRepository,
) {

    suspend operator fun invoke(): Error? = remoteRepository.getMovies()
}