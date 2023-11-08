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
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

    @Mock
    private lateinit var repository: MyMoviesRepository

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    fun setUp() {
        getMovieDetailUseCase = GetMovieDetailUseCase(repository)
    }

    @Test
    operator fun invoke(): Unit = runBlocking {
        //given
        val movie = sampleMovie.copy(id = 1)
        whenever(repository.getMovieDetail(any())).thenReturn(flowOf(movie))

        //when
        val result = getMovieDetailUseCase.invoke(movie.id)

        //then
        result.test {
            assertEquals(awaitItem(), movie)
            verify(repository).getMovieDetail(any())
            awaitComplete()
        }
    }

    @Test
    fun getMovieFromServer(): Unit = runBlocking {
        //given
        val movie = sampleMovie.copy(id = 1)
        whenever(repository.getMovieDetailFromServer(any())).thenReturn(flowOf(movie))

        //when
        val result = getMovieDetailUseCase.getMovieFromServer(movie.id)

        //then
        result.test {
            assertEquals(awaitItem(), movie)
            verify(repository).getMovieDetailFromServer(any())
            awaitComplete()
        }
    }
}
