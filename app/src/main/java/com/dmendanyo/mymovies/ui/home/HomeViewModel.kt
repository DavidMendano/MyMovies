package com.dmendanyo.mymovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.usecases.GetMoviesUseCase
import com.dmendanyo.mymovies.ui.common.CardItemUiModel
import com.dmendanyo.mymovies.ui.common.mapToCardItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableStateFlow<List<CardItemUiModel>>(listOf())
    val movies: StateFlow<List<CardItemUiModel>> = _movies

    init {
        viewModelScope.launch {
            getMoviesUseCase
                .invoke()
                .collect { list ->
                    _movies.value = list.map { it.mapToCardItemUiModel() }
                }
        }
    }
}
