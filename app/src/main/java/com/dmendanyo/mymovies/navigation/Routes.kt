package com.dmendanyo.mymovies.navigation

sealed class Routes(val route: String) {

    object MainScreen : Routes("MainScreen")

    object DetailScreen : Routes("DetailScreen/{id}") {
        fun create(id: Int): String = "DetailScreen/$id"
    }
}
