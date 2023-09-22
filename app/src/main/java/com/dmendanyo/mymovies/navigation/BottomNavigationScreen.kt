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
        BottomNavigationScreen("Home", R.string.home, Icons.Default.Home)

    object Search :
        BottomNavigationScreen("Search", R.string.search, Icons.Default.Search)

    object Favourites :
        BottomNavigationScreen("Favourites", R.string.favourites, Icons.Default.Star)
}