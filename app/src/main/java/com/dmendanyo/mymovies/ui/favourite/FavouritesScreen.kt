package com.dmendanyo.mymovies.ui.favourite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dmendanyo.mymovies.navigation.BottomNavigationScreen

@Composable
fun FavouritesScreen() {
    Text(text = BottomNavigationScreen.Favourites.route)
}
