package com.dmendanyo.mymovies.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dmendanyo.mymovies.navigation.BottomNavigationScreen
import com.dmendanyo.mymovies.ui.favourite.FavoritesScreen
import com.dmendanyo.mymovies.ui.home.HomeScreen
import com.dmendanyo.mymovies.ui.search.SearchScreen
import com.dmendanyo.mymovies.ui.theme.purple500
import com.dmendanyo.mymovies.ui.theme.purple700
import com.dmendanyo.mymovies.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onMovieClicked: (Int) -> Unit,
) {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationScreen.Home,
        BottomNavigationScreen.Search,
        BottomNavigationScreen.Favourites
    )

    Scaffold(
        bottomBar = { BottomBar(navController, bottomNavItems) }
    ) {
        MainScreenNavConfigurations(navController, it, onMovieClicked)
    }
}

@Composable
fun MainScreenNavConfigurations(
    navController: NavHostController,
    padding: PaddingValues,
    onMovieClicked: (Int) -> Unit,
) {
    Box(Modifier.padding(padding)) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationScreen.Home.route
        ) {
            composable(BottomNavigationScreen.Home.route) { HomeScreen(onMovieClicked) }
            composable(BottomNavigationScreen.Search.route) { SearchScreen(onMovieClicked) }
            composable(BottomNavigationScreen.Favourites.route) { FavoritesScreen(onMovieClicked) }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomNavItems: List<BottomNavigationScreen>
) {
    var currentRoute by remember { mutableStateOf(BottomNavigationScreen.Home.route) }
    NavigationBar(containerColor = purple700) {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(indicatorColor = purple500),
                icon = {
                    Icon(
                        screen.icon,
                        stringResource(id = screen.title),
                        tint = white
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.title),
                        color = white
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        currentRoute = screen.route
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}
