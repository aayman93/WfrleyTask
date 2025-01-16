package com.github.aayman93.wfrleytask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.aayman93.wfrleytask.features.orders.presentation.home.HomeScreen

@Composable
fun WfrleyApp(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onCreateOrderClick = {
                    navController.navigate(CreateOrderRoute)
                },
                onOrderClick = { orderId ->
                    navController.navigate(OrderDetailsRoute(orderId))
                }
            )
        }
        composable<OrderDetailsRoute> {

        }
        composable<CreateOrderRoute> {

        }
        composable<FinishOrderRoute> {

        }
    }
}