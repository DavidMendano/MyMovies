package com.dmendanyo.domain.usecases

import com.dmendanyo.domain.repositories.MyMoviesRepository
import javax.inject.Inject

class SwitchLikeUseCase @Inject constructor(
    private val repository: MyMoviesRepository,
) {

    suspend operator fun invoke(id: Int) {
        repository.switchLike(id)
    }
}
