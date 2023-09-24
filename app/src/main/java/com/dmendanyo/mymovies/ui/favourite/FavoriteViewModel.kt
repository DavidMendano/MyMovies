package com.dmendanyo.mymovies.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.usecases.GetFavoritesUseCase
import com.dmendanyo.domain.usecases.SwitchLikeUseCase
import com.dmendanyo.mymovies.ui.common.CardItemUiModel
import com.dmendanyo.mymovies.ui.common.toCardItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val switchLikeUseCase: SwitchLikeUseCase,
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<CardItemUiModel>>(listOf())
    val favorites: StateFlow<List<CardItemUiModel>> = _favorites

    init {
        viewModelScope.launch {
            getFavoritesUseCase.invoke()
                .collect { list ->
                    _favorites.value = list.map { it.toCardItemUiModel() }
                }
        }
    }

    fun switchLike(id: Int) {
        viewModelScope.launch {
            switchLikeUseCase.invoke(id)
        }
    }
}
