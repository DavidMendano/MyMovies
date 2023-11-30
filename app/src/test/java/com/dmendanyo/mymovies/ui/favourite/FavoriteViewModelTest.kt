package com.dmendanyo.mymovies.ui.favourite

import app.cash.turbine.test
import com.dmendanyo.domain.usecases.GetFavoritesUseCase
import com.dmendanyo.domain.usecases.SwitchLikeUseCase
import com.dmendanyo.mymovies.CoroutinesTestRule
import com.dmendanyo.mymovies.common.sampleCardItemUIModel
import com.dmendanyo.mymovies.common.sampleMovie
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: FavoriteViewModel

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Mock
    private lateinit var switchLikeUseCase: SwitchLikeUseCase

    @Before
    fun setUp() {
        getFavoritesUseCase =
            mock { onBlocking { invoke() }.thenReturn(flowOf(listOf(sampleMovie))) }
        viewModel = FavoriteViewModel(getFavoritesUseCase, switchLikeUseCase)
    }

    @Test
    fun `When view model is created, then get favorites`() = runTest {
        // When
        viewModel.favorites.test {
            // Then
            assert(listOf(sampleCardItemUIModel) == awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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