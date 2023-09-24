package com.dmendanyo.mymovies.ui.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmendanyo.mymovies.ui.common.CardItem

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val movies by viewModel.movies.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(movies) {
            CardItem(it) { viewModel.switchLike(it.id) }
        }
    }
}
