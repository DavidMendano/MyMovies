package com.dmendanyo.mymovies.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.dmendanyo.mymovies.R

sealed class BottomNavigationScreen(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object Home :
        BottomNavigationScreen("HomeScreen", R.string.home, Icons.Default.Home)

    object Search :
        BottomNavigationScreen("SearchScreen", R.string.search, Icons.Default.Search)

    object Favourites :
        BottomNavigationScreen("FavouritesScreen", R.string.favourites, Icons.Default.Star)
}