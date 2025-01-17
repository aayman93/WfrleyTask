package com.github.aayman93.wfrleytask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.aayman93.wfrleytask.features.orders.presentation.home.HomeScreen
import com.github.aayman93.wfrleytask.features.orders.presentation.order_details.OrderDetailsScreen

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
            OrderDetailsScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onHomeClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<CreateOrderRoute> {

        }
        composable<FinishOrderRoute> {

        }
    }
}