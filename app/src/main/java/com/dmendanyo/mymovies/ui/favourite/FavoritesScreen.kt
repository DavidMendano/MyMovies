package com.dmendanyo.mymovies.ui.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dmendanyo.mymovies.R
import com.dmendanyo.mymovies.ui.common.CardItem
import com.dmendanyo.mymovies.ui.theme.black

@Composable
fun FavoritesScreen(viewModel: FavoriteViewModel = hiltViewModel()) {
    val movies by viewModel.favorites.collectAsState()

    Box(Modifier.fillMaxSize()) {
        if (movies.isEmpty()) {
            EmptyTitle()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(movies) {
                    CardItem(it) { viewModel.switchLike(it.id) }
                }
            }
        }
    }
}

@Composable
private fun EmptyTitle() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        textAlign = TextAlign.Center,
        color = black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Black,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        text = stringResource(id = R.string.emtpy_favorites_message),
    )
}
