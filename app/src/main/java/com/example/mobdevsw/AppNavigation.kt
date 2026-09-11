package com.example.mobdevsw

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onViewDetails = {
                    navController.navigate("details")
                }
            )
        }

        composable("details") {
            DetailsScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}