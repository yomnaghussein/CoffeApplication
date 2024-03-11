package com.example.coffeapplication.ui.compose_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


const val COFFEE_ID_KEY = "coffeeId"

@Composable
fun CoffeeApplication() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "coffeeList") {
        composable(route = "coffeeList") {
            CoffeeListScreen(navController = navController)
        }
        composable(
            route = "coffeeDetails/{coffeeId}",
            arguments = listOf(navArgument("coffeeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            CoffeeDetailsScreen(
                navBackStackEntry.arguments!!.getInt("coffeeId"), navController
            )
        }
    }
}