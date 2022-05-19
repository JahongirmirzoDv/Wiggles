package com.example.wiggles.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")

    fun withArgs(id: String, name: String, location: String): String {
        return buildString {
            append(route)
            append("/$id")
            append("/$name")
            append("/$location")
        }
    }
}
