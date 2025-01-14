package com.github.aayman93.wfrleytask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun WfrleyApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {

        }
        composable<OrderDetailsRoute> {

        }
        composable<CreateOrderRoute> {

        }
        composable<FinishOrderRoute> {

        }
    }
}