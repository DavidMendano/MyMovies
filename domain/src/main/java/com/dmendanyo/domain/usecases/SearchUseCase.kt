package com.dmendanyo.domain.usecases

import com.dmendanyo.domain.repositories.MyMoviesRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: MyMoviesRepository,
) {

    suspend operator fun invoke(query: String) =
        repository.search(query)
}