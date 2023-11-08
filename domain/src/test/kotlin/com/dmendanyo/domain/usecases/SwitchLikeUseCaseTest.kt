package com.dmendanyo.domain.usecases

import com.dmendanyo.domain.common.sampleMovie
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SwitchLikeUseCaseTest {

    @Mock
    private lateinit var repository: MyMoviesRepository

    private lateinit var switchLikeUseCase: SwitchLikeUseCase

    @Before
    fun setUp() {
        switchLikeUseCase = SwitchLikeUseCase(repository)
    }

    @Test
    operator fun invoke(): Unit = runBlocking {
        //given
        val movie = sampleMovie.copy(id = 1)

        //when
        switchLikeUseCase.invoke(movie.id)

        //then
        verify(repository).switchLike(movie.id)
    }
}
