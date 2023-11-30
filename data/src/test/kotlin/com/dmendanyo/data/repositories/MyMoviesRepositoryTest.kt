package com.dmendanyo.data.repositories

import app.cash.turbine.test
import com.dmendanyo.data.datasources.LocalDataSource
import com.dmendanyo.data.datasources.RemoteDataSource
import com.dmendanyo.domain.repositories.LocationRepository
import com.dmendanyo.domain.repositories.MyMoviesRepository
import com.dmendanyo.domain.usecases.common.sampleMovie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MyMoviesRepositoryTest {

    @Mock
    private lateinit var locationRepository: LocationRepository

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var myMoviesRepository: MyMoviesRepository

    @Before
    fun setUp() {
        myMoviesRepository = MyMoviesRepositoryImpl(
            locationRepository,
            remoteDataSource,
            localDataSource,
        )
    }

    @Test
    fun `When local data source is empty, then call getMovies`(): Unit = runBlocking {
        //given
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(locationRepository.getRegion()).thenReturn("US")
        whenever(remoteDataSource.getMovies(any())).thenReturn(Result.success(listOf()))

        //when
        val result = myMoviesRepository.getMovies()

        //then
        assert(result == null)
        verify(remoteDataSource).getMovies("US")
    }

    @Test
    fun `When local data source is not empty, then doesn't call getMovies`(): Unit = runBlocking {
        //given
        whenever(localDataSource.isEmpty()).thenReturn(false)

        //when
        val result = myMoviesRepository.getMovies()

        //then
        assert(result == null)
        verify(remoteDataSource, times(0)).getMovies("US")
    }

    @Test
    fun `When switch is clicked, then calls switch on local data source`(): Unit = runBlocking {
        //given
        val movie = sampleMovie.copy(id = 1)

        //when
        myMoviesRepository.switchLike(movie.id)

        //then
        verify(localDataSource).switchLike(movie.id)
    }

    @Test
    fun `When get movie detail is called, then calls get movie detail on local data source`(): Unit =
        runBlocking {
            //given
            val movie = sampleMovie.copy(id = 1)

            //when
            myMoviesRepository.getMovieDetail(movie.id)

            //then
            verify(localDataSource).getMovieDetail(movie.id)
        }

    @Test
    fun `When search is called, then calls switch on local data source`(): Unit =
        runBlocking {
            //given
            val movie = sampleMovie.copy(id = 1)
            whenever(remoteDataSource.search(any())).thenReturn(Result.success(listOf(movie)))

            //when
            val result = myMoviesRepository.search("test")

            //then
            result.test {
                assertEquals(awaitItem(), listOf(movie))
                verify(remoteDataSource).search(any())
                awaitComplete()
            }
        }

    @Test
    fun `When get movie detail from server is called, then retrieve movie from server`(): Unit =
        runBlocking {
            //given
            val movie = sampleMovie.copy(id = 1)
            whenever(remoteDataSource.getMovieById(any())).thenReturn(Result.success(movie))

            //when
            val result = myMoviesRepository.getMovieDetailFromServer(movie.id)

            //then
            result.test {
                assertEquals(awaitItem(), movie)
                verify(remoteDataSource).getMovieById(any())
                awaitComplete()
            }
        }
}
