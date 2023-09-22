package com.dmendanyo.mymovies.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dmendanyo.mymovies.navigation.BottomNavigationScreen
import com.dmendanyo.mymovies.ui.favourite.FavouritesScreen
import com.dmendanyo.mymovies.ui.home.HomeScreen
import com.dmendanyo.mymovies.ui.search.SearchScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationScreen.Home,
        BottomNavigationScreen.Search,
        BottomNavigationScreen.Favourites
    )

    Scaffold(
        bottomBar = { BottomBar(navController, bottomNavItems) }
    ) {
        MainScreenNavConfigurations(navController, it)
    }
}

@Composable
fun MainScreenNavConfigurations(
    navController: NavHostController,
    padding: PaddingValues
) {
    Box(Modifier.padding(padding)) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationScreen.Home.route
        ) {
            composable(BottomNavigationScreen.Home.route) { HomeScreen() }
            composable(BottomNavigationScreen.Search.route) { SearchScreen() }
            composable(BottomNavigationScreen.Favourites.route) { FavouritesScreen() }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomNavItems: List<BottomNavigationScreen>
) {
    var currentRoute by remember { mutableStateOf(BottomNavigationScreen.Home.route) }
    BottomNavigation {
        bottomNavItems.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, stringResource(id = screen.title)) },
                label = { Text(text = stringResource(id = screen.title)) },
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
