package com.example.wiggles.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wiggles.view.Detail
import com.example.wiggles.view.Home

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            Home(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route+"/{id}/{name}/{location}",
            arguments = listOf(
                navArgument(
                    "id"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument(
                    "name"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument(
                    "location"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) {entry->
            Detail(entry.arguments,navController)
        }
    }
}