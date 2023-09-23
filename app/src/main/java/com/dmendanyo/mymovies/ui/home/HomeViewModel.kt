package com.dmendanyo.mymovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.models.Error
import com.dmendanyo.domain.usecases.GetMoviesUseCase
import com.dmendanyo.domain.usecases.RequestMoviesUseCase
import com.dmendanyo.mymovies.ui.common.CardItemUiModel
import com.dmendanyo.mymovies.ui.common.mapToCardItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val requestMoviesUseCase: RequestMoviesUseCase,
) : ViewModel() {

    private val _error = MutableStateFlow<Error?>(null)
    val error: StateFlow<Error?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _movies = MutableStateFlow<List<CardItemUiModel>>(listOf())
    val movies: StateFlow<List<CardItemUiModel>> = _movies

    init {
        viewModelScope.launch {
            getMoviesUseCase.invoke()
                .collect { list ->
                    _movies.value = list.map { it.mapToCardItemUiModel() }
                }
        }
    }

    fun onUIReady() {
        viewModelScope.launch {
            _loading.value = true
            val error = requestMoviesUseCase.invoke()
            _error.value = error
        }
    }
}
