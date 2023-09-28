package com.dmendanyo.mymovies.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmendanyo.domain.usecases.SearchUseCase
import com.dmendanyo.mymovies.eventbus.showLoader
import com.dmendanyo.mymovies.ui.common.CardItemUiModel
import com.dmendanyo.mymovies.ui.common.toCardItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _active = MutableStateFlow(false)
    val active: StateFlow<Boolean> = _active

    private val _results = MutableStateFlow<List<CardItemUiModel>>(listOf())
    val results: StateFlow<List<CardItemUiModel>> = _results

    fun search(query: String) {
        viewModelScope.launch {
            showLoader(true)
            searchUseCase.invoke(query)
                .collect { list -> _results.value = list.map { it.toCardItemUiModel() } }
            showLoader(false)
        }
    }

    fun onActiveChanged(isActive: Boolean) {
        _active.value = isActive
    }

    fun onQueryChanged(query: String) {
        _query.value = query
    }
}
