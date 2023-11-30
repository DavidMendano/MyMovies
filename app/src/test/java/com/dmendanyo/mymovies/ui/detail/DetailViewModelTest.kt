package com.dmendanyo.mymovies.ui.detail

import com.dmendanyo.domain.usecases.GetMovieDetailUseCase
import com.dmendanyo.domain.usecases.SwitchLikeUseCase
import com.dmendanyo.mymovies.CoroutinesTestRule
import com.dmendanyo.mymovies.common.sampleMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
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
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    private lateinit var switchLikeUseCase: SwitchLikeUseCase

    @Before
    fun setUp() {
        viewModel = DetailViewModel(getMovieDetailUseCase, switchLikeUseCase)
    }

    @Test
    fun `When view model is created, then get movie`() = runTest {
        //given
        whenever(getMovieDetailUseCase.invoke(any())).thenReturn(flowOf(null))
        whenever(getMovieDetailUseCase.getMovieFromServer(any())).thenReturn(flowOf(sampleMovie))

        //when
        viewModel.getMovieDetail(any())
        runCurrent()

        //then
        assert(viewModel.isFromServer.value)
        verify(getMovieDetailUseCase).getMovieFromServer(any())
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