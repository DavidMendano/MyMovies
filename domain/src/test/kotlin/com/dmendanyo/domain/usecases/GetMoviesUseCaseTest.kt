package com.dmendanyo.domain.usecases

import app.cash.turbine.test
import com.dmendanyo.domain.common.sampleMovie
import com.dmendanyo.domain.repositories.MyMoviesRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {

    @Mock
    private lateinit var repository: MyMoviesRepository

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        getMoviesUseCase = GetMoviesUseCase(repository)
    }

    @Test
    operator fun invoke(): Unit = runBlocking {
        //given
        val movie = sampleMovie.copy(id = 1)
        whenever(repository.movies).thenReturn(flowOf(listOf(movie)))

        //when
        val result = getMoviesUseCase.invoke()

        //then
        result.test {
            assertEquals(awaitItem(), listOf(movie))
            verify(repository).movies
            awaitComplete()
        }
    }
}
