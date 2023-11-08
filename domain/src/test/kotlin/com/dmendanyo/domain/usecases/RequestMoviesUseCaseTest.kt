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
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RequestMoviesUseCaseTest {

    @Mock
    private lateinit var repository: MyMoviesRepository

    private lateinit var requestMoviesUseCase: RequestMoviesUseCase

    @Before
    fun setUp() {
        requestMoviesUseCase = RequestMoviesUseCase(repository)
    }

    @Test
    operator fun invoke(): Unit = runBlocking {
        //given
        val movie = sampleMovie.copy(id = 1)
        whenever(repository.getMovies()).thenReturn(null)

        //when
        val result = requestMoviesUseCase.invoke()

        //then
        assert(result == null)
        verify(repository).getMovies()
    }
}
