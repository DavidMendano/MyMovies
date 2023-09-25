package com.dmendanyo.mymovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.extensions.mapToError
import com.dmendanyo.domain.usecases.GetMoviesUseCase
import com.dmendanyo.domain.usecases.RequestMoviesUseCase
import com.dmendanyo.domain.usecases.SwitchLikeUseCase
import com.dmendanyo.mymovies.eventbus.showLoader
import com.dmendanyo.mymovies.extensions.throwError
import com.dmendanyo.mymovies.ui.common.CardItemUiModel
import com.dmendanyo.mymovies.ui.common.toCardItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val requestMoviesUseCase: RequestMoviesUseCase,
    private val switchLikeUseCase: SwitchLikeUseCase,
) : ViewModel() {

    private val _movies = MutableStateFlow<List<CardItemUiModel>>(listOf())
    val movies: StateFlow<List<CardItemUiModel>> = _movies

    init {
        viewModelScope.launch {
            fetchMoviesFromServer()
            getMoviesUseCase.invoke()
                .catch { error -> error.mapToError().throwError("Error fetching movies from DB") }
                .collect { list -> _movies.value = list.map { it.toCardItemUiModel() } }
        }
    }

    private suspend fun fetchMoviesFromServer() {
        showLoader(true)
        requestMoviesUseCase.invoke()?.throwError("Error fetching movies from Server")
        showLoader(false)
    }

    fun switchLike(id: Int) {
        viewModelScope.launch {
            switchLikeUseCase.invoke(id)
        }
    }
}
