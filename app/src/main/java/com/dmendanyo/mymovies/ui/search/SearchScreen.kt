package com.dmendanyo.mymovies.ui.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dmendanyo.mymovies.navigation.BottomNavigationScreen

@Composable
fun SearchScreen() {
    Text(text = BottomNavigationScreen.Search.route)
}
