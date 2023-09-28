package com.dmendanyo.mymovies.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmendanyo.mymovies.ui.common.CardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onMovieClicked: (Int) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsState()
    val active by viewModel.active.collectAsState()
    val results by viewModel.results.collectAsState()

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        query = query,
        onQueryChange = viewModel::onQueryChanged,
        onSearch = viewModel::search,
        active = active,
        onActiveChange = viewModel::onActiveChanged,
        trailingIcon = {
            IconButton(onClick = { viewModel.search(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon"
                )
            }
        }
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(results) { item ->
                CardItem(item = item, onMovieClicked = onMovieClicked)
            }
        }
    }
}
