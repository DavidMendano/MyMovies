package com.dmendanyo.mymovies.ui.home

import app.cash.turbine.test
import com.dmendanyo.domain.usecases.GetMoviesUseCase
import com.dmendanyo.domain.usecases.RequestMoviesUseCase
import com.dmendanyo.domain.usecases.SwitchLikeUseCase
import com.dmendanyo.mymovies.CoroutinesTestRule
import com.dmendanyo.mymovies.common.sampleCardItemUIModel
import com.dmendanyo.mymovies.common.sampleMovie
import com.dmendanyo.mymovies.ui.common.CardItemUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Mock
    private lateinit var requestMoviesUseCase: RequestMoviesUseCase

    @Mock
    private lateinit var switchLikeUseCase: SwitchLikeUseCase

    @Before
    fun setUp() {
        viewModel = HomeViewModel(getMoviesUseCase, requestMoviesUseCase, switchLikeUseCase)
    }

    @Test
    fun `When view model is created, then get movies`() = runTest {
        // Given
        whenever(getMoviesUseCase.invoke()).thenReturn(flowOf(listOf(sampleMovie)))
        whenever(requestMoviesUseCase.invoke()).thenReturn(null)

        // When
        viewModel.movies.test {
            viewModel.onUiReady()

            // Then
            val initialState = awaitItem()
            assert(emptyList<CardItemUiModel>() == initialState)
            val response = awaitItem()
            assert(listOf(sampleCardItemUIModel) == response)
        }
    }

    @Test
    fun `When switch like is called, then call use case is called`() = runTest {
        // Given
        whenever(switchLikeUseCase.invoke(any())).thenReturn(Unit)

        // When
        viewModel.switchLike(any())
        runCurrent()

        // Then
        verify(switchLikeUseCase).invoke(any())
    }
}
